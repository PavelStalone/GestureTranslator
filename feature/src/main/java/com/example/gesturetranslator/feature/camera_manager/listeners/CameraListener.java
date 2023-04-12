package com.example.gesturetranslator.feature.camera_manager.listeners;

import com.example.gesturetranslator.feature.camera_manager.models.ImageFromCamera;

public interface CameraListener {
    void getImage(ImageFromCamera imageFromCamera);
    void error(Exception exception);
}
