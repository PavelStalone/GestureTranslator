package com.ortin.gesturetranslator.core.managers.model_coordinate.models;

public class ModelCoordinateClassificationArray {
    private final float percent;
    private final String label;

    public ModelCoordinateClassificationArray(float percent, String label) {
        this.percent = percent;
        this.label = label;
    }

    public float getPercent() {
        return percent;
    }

    public String getLabel() {
        return label;
    }
}
