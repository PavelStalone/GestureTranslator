package com.example.gesturetranslator.core.managers.mediapipe_manager;

import com.example.gesturetranslator.core.managers.mediapipe_manager.listeners.MPDetectionListener;
import com.example.gesturetranslator.core.managers.mediapipe_manager.models.MPImageInput;

public interface MediaPipeManager {
    void detectImage(MPImageInput mpImageInput);

    void setMPDetectionListener(MPDetectionListener mpDetectionListener);
}
