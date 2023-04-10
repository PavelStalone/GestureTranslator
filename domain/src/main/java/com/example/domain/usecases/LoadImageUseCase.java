package com.example.domain.usecases;

import com.example.domain.models.Image;
import com.example.domain.repository.LoadImageRepository;

public class LoadImageUseCase {

    final LoadImageRepository loadImageRepository;

    public LoadImageUseCase(LoadImageRepository loadImageRepository) {
        this.loadImageRepository = loadImageRepository;
    }

    public Image execute() {
        return null;
    }
}
