package com.ortin.gesturetranslator.presentation;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ortin.gesturetranslator.components.OnChangedStatusListener;
import com.ortin.gesturetranslator.databinding.MainFrameBinding;
import com.ortin.gesturetranslator.app.models.MainFrameState;
import com.ortin.gesturetranslator.app.models.PredictState;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {
    private MainFrameBinding binding;
    private static final String TAG = "MainFrame";

    private MainViewModel viewModel;

    private PredictState lastPredictState;
    private MainFrameState lastMainFrameState;

    private ActivityResultLauncher<String> mGetContent;
    private ActivityResultLauncher<Intent> pickVisualLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        registerPermissionListener();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainFrameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkPermission();
        registerActivityForPickImage();
        init();
        initListeners();
    }

    private void registerPermissionListener() {
        mGetContent = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    checkPermission();
                } else {
                    mGetContent.launch(Manifest.permission.CAMERA);
                }
            }
        });
    }

    private void registerActivityForPickImage() {
        pickVisualLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri uri = result.getData().getData();
                Log.d("PhotoPicker", "Selected URI: " + uri);

                Navigation.findNavController(binding.getRoot()).navigate(MainFragmentDirections.actionToImageFromGalleryFragment(uri));
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            mGetContent.launch(Manifest.permission.CAMERA);
        } else {
            viewModel.startRealTimeImagining(getViewLifecycleOwner());
        }
    }

    private void init() {
        lastPredictState = null;
        lastMainFrameState = null;

        binding.bottomSheetBehaviorLayout.bottomSheetBehavior.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.bottomSheetBehaviorLayout.bottomSheetBehavior.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                DisplayMetrics displayMetrics = new DisplayMetrics();
                requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                BottomSheetBehavior.from(binding.bottomSheetBehaviorLayout.bottomSheetBehavior).setPeekHeight(displayMetrics.heightPixels - binding.imageWithPredict.preview.getBottom());
            }
        });
    }

    private void initListeners() {
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetBehaviorLayout.bottomSheetBehavior);

        viewModel.getMainLiveData().observe(getViewLifecycleOwner(), state -> {
            if (lastMainFrameState == null) {
                binding.controlMenu.realTimeBTN.setState(state.isRealtimeButton());
                binding.controlMenu.flashLight.setState(state.isFlashlight());
                bottomSheetBehavior.setState(state.getBottomSheetBehavior());
            } else {
                if (lastMainFrameState.isRealtimeButton() != state.isRealtimeButton())
                    binding.controlMenu.realTimeBTN.setState(state.isRealtimeButton());
                if (lastMainFrameState.isFlashlight() != state.isFlashlight())
                    binding.controlMenu.flashLight.setState(state.isFlashlight());
                if (lastMainFrameState.getBottomSheetBehavior() != state.getBottomSheetBehavior())
                    bottomSheetBehavior.setState(state.getBottomSheetBehavior());
            }

            lastMainFrameState = state;
        });

        viewModel.getPredictLiveData().observe(getViewLifecycleOwner(), state -> {
            if (lastPredictState == null) {
                binding.imageWithPredict.preview.setImageBitmap(state.getImageFromCamera());
                binding.imageWithPredict.wordPredictTV.setText(state.getPredictLetter());
                binding.bottomSheetBehaviorLayout.recognizeTextResult.setText(state.getPredictWord());
            } else {
                if (lastPredictState.getImageFromCamera() != state.getImageFromCamera())
                    binding.imageWithPredict.preview.setImageBitmap(state.getImageFromCamera());
                if (!lastPredictState.getPredictLetter().equals(state.getPredictLetter()))
                    binding.imageWithPredict.wordPredictTV.setText(state.getPredictLetter());
                if (!lastPredictState.getPredictWord().equals(state.getPredictWord()))
                    binding.bottomSheetBehaviorLayout.recognizeTextResult.setText(state.getPredictWord());
            }
            if (state.getCoordinateHand() != null)
                binding.imageWithPredict.paintHandView.drawHand(state.getCoordinateHand());
            else binding.imageWithPredict.paintHandView.clear();

            lastPredictState = state;
        });

        binding.controlMenu.realTimeBTN.setOnChangedStatusListener(new OnChangedStatusListener() {
            @Override
            public void onStart() {
                viewModel.onStartRealTimeButton();
            }

            @Override
            public void onStop() {
                viewModel.onStopRealTimeButton();
            }
        });

        binding.controlMenu.flashLight.setOnChangedStatusListener(new OnChangedStatusListener() {
            @Override
            public void onStart() {
                viewModel.onFlashLight();
            }

            @Override
            public void onStop() {
                viewModel.offFlashLight();
            }
        });

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED -> viewModel.bottomSheetExpanded();
                    case BottomSheetBehavior.STATE_COLLAPSED -> viewModel.bottomSheetCollapsed();
                    default -> {
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        binding.controlMenu.galleryLL.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {
                binding.controlMenu.gallery.setProgress(0);
                binding.controlMenu.gallery.playAnimation();
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");

                pickVisualLauncher.launch(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.offFlashLight();
        binding = null;
    }
}
