package com.ortin.gesturetranslator.core.managers.mediapipe.models;

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
