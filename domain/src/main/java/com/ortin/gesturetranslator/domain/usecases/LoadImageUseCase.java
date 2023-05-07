package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.repository.LoadImageRepository;
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;

public class LoadImageUseCase {

    final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public void execute(LoadImagesListener loadImagesListener) {
        loadImageRepository.loadImages(loadImagesListener);
    }
}
