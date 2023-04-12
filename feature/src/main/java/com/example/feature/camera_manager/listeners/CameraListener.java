package com.example.feature.camera_manager.listeners;

import com.example.feature.camera_manager.models.ImageFromCamera;

public interface CameraListener {
    void getImage(ImageFromCamera imageFromCamera);
    void error(Exception exception);
}
