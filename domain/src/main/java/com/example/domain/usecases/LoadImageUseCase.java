package com.example.domain.usecases;

import com.example.domain.repository.LoadImageRepository;
import com.example.domain.listeners.LoadImagesInterface;

public class LoadImageUseCase {

    final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public void execute(LoadImagesInterface loadImagesInterface) {
        loadImageRepository.loadImages(loadImagesInterface);
    }
}
