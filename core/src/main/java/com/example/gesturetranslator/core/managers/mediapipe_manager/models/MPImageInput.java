package com.example.gesturetranslator.core.managers.mediapipe_manager.models;

import com.google.mediapipe.framework.image.MPImage;

public class MPImageInput {
    private final MPImage mpImageInput;

    public MPImageInput(MPImage mpImageInput) {
        this.mpImageInput = mpImageInput;
    }

    public MPImage getMpImage() {
        return mpImageInput;
    }
}
