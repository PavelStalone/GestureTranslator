package com.ortin.gesturetranslator.feature.managers.camera_manager.listeners;

import com.ortin.gesturetranslator.feature.managers.camera_manager.models.ImageFromCamera;

public interface CameraListener {
    void getImage(ImageFromCamera imageFromCamera);
    void error(Exception exception);
}
