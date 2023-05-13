package com.ortin.gesturetranslator.domain.usecases;

import android.app.Fragment;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository;

public class LoadImageUseCase {

    private final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public void execute(LoadImagesListener loadImagesListener, LifecycleOwner lifecycleOwner) {
        loadImageRepository.loadImages(loadImagesListener, lifecycleOwner);
    }
}
