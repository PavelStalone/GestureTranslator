package com.ortin.gesturetranslator.data.models;

public class Settings {
    private boolean theme;
    private boolean gpu;
    private boolean percent;
    private int speedFrameDetection;

    public Settings setTheme(boolean theme) {
        this.theme = theme;
        return this;
    }

    public Settings setGpu(boolean gpu) {
        this.gpu = gpu;
        return this;
    }

    public Settings setPercent(boolean percent) {
        this.percent = percent;
        return this;
    }

    public Settings setSpeedFrameDetection(int speedFrameDetection) {
        this.speedFrameDetection = speedFrameDetection;
        return this;
    }

    public boolean isTheme() {
        return theme;
    }

    public boolean isGpu() {
        return gpu;
    }

    public boolean isPercent() {
        return percent;
    }

    public int getSpeedFrameDetection() {
        return speedFrameDetection;
    }
}
