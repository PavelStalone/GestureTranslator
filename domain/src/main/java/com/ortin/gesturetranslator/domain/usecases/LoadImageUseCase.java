package com.ortin.gesturetranslator.domain.usecases;

import android.app.Activity;

import com.ortin.gesturetranslator.domain.repository.LoadImageRepository;
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;

public class LoadImageUseCase {

    private final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public void execute(LoadImagesListener loadImagesListener, Activity activity) {
        loadImageRepository.loadImages(loadImagesListener, activity);
    }
}
