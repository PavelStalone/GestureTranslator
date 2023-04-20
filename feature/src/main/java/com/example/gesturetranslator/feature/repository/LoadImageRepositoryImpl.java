package com.example.gesturetranslator.feature.repository;

import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.domain.listeners.LoadImagesListener;
import com.example.gesturetranslator.feature.managers.camera_manager.listeners.CameraListener;
import com.example.gesturetranslator.feature.managers.camera_manager.CameraManager;
import com.example.gesturetranslator.feature.managers.camera_manager.models.ImageFromCamera;

public class LoadImageRepositoryImpl implements LoadImageRepository {
    private final CameraManager cameraManager;

    public LoadImageRepositoryImpl(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    @Override
    public void loadImages(LoadImagesListener loadImagesListener) {
        cameraManager.loadImage(mapperToDomainListener(loadImagesListener));
    }





    // Правила перевода для связи domain и feature модулей

    private Image mapToDomain(ImageFromCamera imageFromCamera) {
        return new Image(imageFromCamera.getImage(), imageFromCamera.getRotaion());
    }

    private CameraListener mapperToDomainListener(LoadImagesListener loadImagesListener) {
        return new CameraListener() {
            @Override
            public void getImage(ImageFromCamera imageFromCamera) {
                loadImagesListener.getImage(mapToDomain(imageFromCamera));
            }

            @Override
            public void error(Exception exception) {
                loadImagesListener.error(exception);
            }
        };
    }
}
