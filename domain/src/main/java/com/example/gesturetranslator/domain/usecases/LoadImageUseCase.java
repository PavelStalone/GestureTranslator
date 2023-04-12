package com.example.gesturetranslator.domain.usecases;

import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.domain.listeners.LoadImagesInterface;

public class LoadImageUseCase {

    final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public void execute(LoadImagesInterface loadImagesInterface) {
        loadImageRepository.loadImages(loadImagesInterface);
    }
}
