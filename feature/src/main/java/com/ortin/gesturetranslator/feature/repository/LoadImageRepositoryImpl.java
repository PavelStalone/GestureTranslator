package com.ortin.gesturetranslator.feature.repository;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository;
import com.ortin.gesturetranslator.feature.managers.camera.CameraManager;
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener;
import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera;

public class LoadImageRepositoryImpl implements LoadImageRepository {
    private final CameraManager cameraManager;

    public LoadImageRepositoryImpl(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    @Override
    public void loadImages(LoadImagesListener loadImagesListener, LifecycleOwner lifecycleOwner) {
        cameraManager.loadImage(mapperToDomainListener(loadImagesListener), lifecycleOwner);
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
