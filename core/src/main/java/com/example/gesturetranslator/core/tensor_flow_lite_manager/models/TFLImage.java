package com.example.gesturetranslator.core.tensor_flow_lite_manager.models;

import org.tensorflow.lite.support.image.TensorImage;

public class TFLImage {
    private final TensorImage tensorImage;
    private final int rotation;

    public TFLImage(TensorImage tensorImage, int rotation) {
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
