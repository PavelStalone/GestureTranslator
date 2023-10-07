package com.ortin.gesturetranslator.presentation;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ortin.gesturetranslator.databinding.ImageFromGalleryLayoutBinding;
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener;
import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.models.ImageDetected;
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

@AndroidEntryPoint
public class ImageFromGalleryFragment extends Fragment implements DetectionHandListener {
    private ImageFromGalleryLayoutBinding binding;

    @Inject
    DetectHandUseCase detectHandUseCase;

    @Inject
    RecognizeCoordinateUseCase recognizeCoordinateUseCase;

    private ActivityResultLauncher<Intent> pickVisualLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ImageFromGalleryLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerActivityForPickImage();
        init();
        initListeners();
    }

    @SuppressLint("IntentReset")
    private void initListeners() {
        binding.galleryButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");

            pickVisualLauncher.launch(intent);
        });
    }

    private void init() {
        Uri uri = ImageFromGalleryFragmentArgs.fromBundle(getArguments()).getImage();
        binding.previewFromGallery.setImageURI(uri);
        detectHandUseCase.setOnDetectionHandListener(this);

        predictUri(uri);
    }

    private void registerActivityForPickImage() {
        pickVisualLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri uri = result.getData().getData();
                binding.previewFromGallery.setImageURI(uri);
                predictUri(uri);
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });
    }

    private void predictUri(Uri uri) {
        Glide.with(requireContext())
                .asBitmap()
                .load(uri)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Log.e("Gallery", "onResourceReady: " + resource);
                        detectHandUseCase.execute(resource);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void detect(ImageDetected imageDetected) {
        Log.e("Gallery", "detect: " + imageDetected);
        if (imageDetected != null) {
            CoordinateClassification coordinateClassification = recognizeCoordinateUseCase.execute(imageDetected);

            String predictLetter = String.format("%s", coordinateClassification.getLabel());
            Log.e("Gallery", "label: " + coordinateClassification.getLabel());
            Observable.just(predictLetter).subscribeOn(AndroidSchedulers.mainThread()).subscribe(t -> {
                if (binding != null) {
                    binding.letterFromGallery.setText(t);
                }
            });
        } else {
            Observable.just("None").subscribeOn(AndroidSchedulers.mainThread()).subscribe(t -> {
                if (binding != null) {
                    binding.letterFromGallery.setText(t);
                }
            });
        }
    }

    @Override
    public void error(Exception exception) {

    }
}
