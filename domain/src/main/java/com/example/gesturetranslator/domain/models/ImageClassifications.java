package com.example.gesturetranslator.domain.models;

public class ImageClassifications {
    private final String label;
    private final float percent;

    public ImageClassifications(String label, float percent) {
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
