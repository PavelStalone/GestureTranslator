package com.ortin.gesturetranslator.domain.models;

public class ImageClassification {
    private final String label;
    private final float percent;

    public ImageClassification(String label, float percent) {
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
