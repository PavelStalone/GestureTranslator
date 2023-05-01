package com.example.gesturetranslator.core.managers.mediapipe_manager.models;

import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult;

public class MPDetection {
    private final HandLandmarkerResult result;

    public MPDetection(HandLandmarkerResult result) {
        this.result = result;
    }

    public HandLandmarkerResult getResult() {
        return result;
    }
}
