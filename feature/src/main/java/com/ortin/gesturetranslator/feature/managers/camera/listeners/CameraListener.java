package com.ortin.gesturetranslator.feature.managers.camera.listeners;

import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera;

public interface CameraListener {
    void getImage(ImageFromCamera imageFromCamera);
    void error(Exception exception);
}
