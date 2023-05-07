package com.ortin.gesturetranslator.core.managers.mediapipe_manager.listeners;

import com.ortin.gesturetranslator.core.managers.mediapipe_manager.models.MPDetection;

public interface MPDetectionListener {
    void detect(MPDetection mpDetection);

    void error(Exception exception);
}
