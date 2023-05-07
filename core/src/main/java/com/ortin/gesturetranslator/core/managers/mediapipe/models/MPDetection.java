package com.ortin.gesturetranslator.core.managers.mediapipe.models;

import com.google.mediapipe.framework.image.MPImage;
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult;

public class MPDetection {
    private final HandLandmarkerResult result;
    private final MPImage mpImage;

    public MPDetection(HandLandmarkerResult result, MPImage mpImage) {
        this.result = result;
        this.mpImage = mpImage;
    }

    public HandLandmarkerResult getResult() {
        return result;
    }

    public MPImage getMpImage() {
        return mpImage;
    }
}
