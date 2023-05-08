package com.ortin.gesturetranslator.domain.models;

public class CoordinateClassification {
    private final String label;
    private final float percent;

    public CoordinateClassification(String label, float percent) {
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
