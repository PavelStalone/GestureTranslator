package com.ortin.gesturetranslator.domain.models;

public class HandDetected {
    private final float[] coordinates;

    public HandDetected(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public float[] getCoordinates() {
        return coordinates;
    }
}
