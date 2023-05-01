package com.example.gesturetranslator.core.managers.mediapipe_manager.listeners;

import com.example.gesturetranslator.core.managers.mediapipe_manager.models.MPDetection;

public interface MPDetectionListener {
    void detect(MPDetection mpDetection);

    void error(Exception exception);
}
