package com.example.feature.repository;

import com.example.domain.models.Image;
import com.example.domain.repository.LoadImageRepository;
import com.example.feature.camera_manager.CameraManager;
import com.example.feature.camera_manager.models.ImageFromCamera;

public class LoadImageRepositoryImpl implements LoadImageRepository {
    private final CameraManager cameraManager;

    public LoadImageRepositoryImpl(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    @Override
    public Image getImage() {
        Image image = mapToDomain(cameraManager.loadImage());

        return image;
    }

    private Image mapToDomain(ImageFromCamera imageFromCamera) {
        return new Image(imageFromCamera.getImage(), imageFromCamera.getRotaion());
    }
}
