package com.example.gesturetranslator.feature.repository;

import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.domain.listeners.LoadImagesInterface;
import com.example.gesturetranslator.feature.camera_manager.listeners.CameraListener;
import com.example.gesturetranslator.feature.camera_manager.CameraManager;
import com.example.gesturetranslator.feature.camera_manager.models.ImageFromCamera;

public class LoadImageRepositoryImpl implements LoadImageRepository {
    private final CameraManager cameraManager;

    public LoadImageRepositoryImpl(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    @Override
    public Image loadImages(LoadImagesInterface loadImagesInterface) {
        cameraManager.loadImage(mapperToDomainListener(loadImagesInterface));
        return null;
    }





    // Правила перевода для связи domain и feature модулей

    private Image mapToDomain(ImageFromCamera imageFromCamera) {
        return new Image(imageFromCamera.getImage(), imageFromCamera.getRotaion());
    }

    private CameraListener mapperToDomainListener(LoadImagesInterface loadImagesInterface) {
        return new CameraListener() {
            @Override
            public void getImage(ImageFromCamera imageFromCamera) {
                loadImagesInterface.getImage(mapToDomain(imageFromCamera));
            }

            @Override
            public void error(Exception exception) {
                loadImagesInterface.error(exception);
            }
        };
    }
}
