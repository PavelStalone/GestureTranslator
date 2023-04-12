package com.example.gesturetranslator.domain.usecases;

import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.domain.listeners.LoadImagesListener;

public class LoadImageUseCase {

    final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public void execute(LoadImagesListener loadImagesListener) {
        loadImageRepository.loadImages(loadImagesListener);
    }
}
