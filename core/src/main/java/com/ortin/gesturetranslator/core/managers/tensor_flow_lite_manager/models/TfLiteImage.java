package com.ortin.gesturetranslator.core.managers.tensor_flow_lite_manager.models;

import org.tensorflow.lite.support.image.TensorImage;

public class TfLiteImage {
    private final TensorImage tensorImage;
    private final int rotation;

    public TfLiteImage(TensorImage tensorImage, int rotation) {
        this.tensorImage = tensorImage;
        this.rotation = rotation;
    }

    public TensorImage getTensorImage() {
        return tensorImage;
    }

    public int getRotation() {
        return rotation;
    }
}
