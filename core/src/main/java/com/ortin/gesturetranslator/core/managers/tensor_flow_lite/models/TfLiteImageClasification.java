package com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models;

public class TfLiteImageClasification {
    private final String label;
    private final float percent;

    public TfLiteImageClasification(String label, float percent) {
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
