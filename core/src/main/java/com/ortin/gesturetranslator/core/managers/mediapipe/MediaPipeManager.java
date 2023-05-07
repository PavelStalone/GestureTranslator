package com.ortin.gesturetranslator.core.managers.mediapipe;

import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener;
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput;

public interface MediaPipeManager {
    void detectImage(MPImageInput mpImageInput);

    void setMPDetectionListener(MPDetectionListener mpDetectionListener);
}
