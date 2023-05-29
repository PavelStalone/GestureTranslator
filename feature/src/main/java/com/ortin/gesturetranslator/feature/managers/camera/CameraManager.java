package com.ortin.gesturetranslator.feature.managers.camera;

import androidx.lifecycle.LifecycleOwner;

import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener;

public interface CameraManager {
    void loadImage(CameraListener cameraListener, LifecycleOwner lifecycleOwner);
    void setStatusFlashlight(boolean mode);
}
