package com.example.gesturetranslator.presentation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.gesturetranslator.R;
import com.example.gesturetranslator.databinding.MainFrameBinding;
import com.example.gesturetranslator.domain.listeners.LoadImagesListener;
import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.models.ImageClassifications;
import com.example.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.example.gesturetranslator.domain.usecases.RecognizeImageUseCase;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment implements LoadImagesListener, RecognizeImageListener {
    private MainFrameBinding binding;
    private Context context;

    private static final String TAG = "MainFrame";
    private static final int PERMISSION_REQUEST_CAMERA = 23;
    private BottomSheetBehavior bottomSheetBehavior;

    @Inject
    LoadImageUseCase loadImageUseCase;

    @Inject
    RecognizeImageUseCase recognizeImageUseCase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = Constant.MAIN;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainFrameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        initListeners();
        start();
    }

    public void init() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetBehaviorLayout.bottomSheetBehavior);

        binding.bottomSheetBehaviorLayout.bottomSheetBehavior.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.bottomSheetBehaviorLayout.bottomSheetBehavior.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((MainActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                bottomSheetBehavior.setPeekHeight(displayMetrics.heightPixels - binding.imageWithPredict.preview.getBottom() - binding.imageWithPredict.wordPredictTV.getHeight());
            }
        });
    }

    private void start() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        } else {
            //starting();
            loadImageUseCase.execute(this);
            recognizeImageUseCase.setOnRecogniseListener(this);
        }
    }

    boolean flag = false;

    private void initListeners() {
        binding.controlMenu.realTimeLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                if (flag) {
                    binding.controlMenu.realTimeLV.setAnimation(R.raw.start_stop);
                    binding.controlMenu.realTimeLV.playAnimation();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    binding.controlMenu.realTimeLV.setAnimation(R.raw.stop_start);
                    binding.controlMenu.realTimeLV.playAnimation();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            start();
        }
    }

    @Override
    public void getImage(Image image) {
        Bitmap bitmap = image.getBitmap();
        int rotation = image.getRotaion();

        binding.imageWithPredict.preview.setRotation(rotation);
        binding.imageWithPredict.preview.setImageBitmap(bitmap);

        recognizeImageUseCase.execute(image);
    }

    @Override
    public void recognise(ImageClassifications imageClassifications) {
        binding.imageWithPredict.wordPredictTV.setText(String.format("%s %.2f", imageClassifications.getLabel(), imageClassifications.getPercent()) + "%");
    }

    @Override
    public void error(Exception exception) {
        Log.e(TAG, "Error!  [!]");
        exception.printStackTrace();
    }
}
