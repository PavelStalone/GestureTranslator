package com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models;

public class TFLImageClasification {
    private final String label;
    private final float percent;

    public TFLImageClasification(String label, float percent) {
        this.label = label;
        this.percent = percent;
    }

    public String getLabel() {
        return label;
    }

    public float getPercent() {
        return percent;
    }
}
