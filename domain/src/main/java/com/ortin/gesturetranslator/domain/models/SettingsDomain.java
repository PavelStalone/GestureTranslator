package com.ortin.gesturetranslator.domain.models;

public class SettingsDomain {
    private boolean theme;
    private boolean gpu;
    private boolean percent;
    private int speedFrameDetection;

    public SettingsDomain setTheme(boolean theme) {
        this.theme = theme;
        return this;
    }

    public SettingsDomain setGpu(boolean gpu) {
        this.gpu = gpu;
        return this;
    }

    public SettingsDomain setPercent(boolean percent) {
        this.percent = percent;
        return this;
    }

    public SettingsDomain setSpeedFrameDetection(int speedFrameDetection) {
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
