package com.ortin.gesturetranslator.data.storage;

import com.ortin.gesturetranslator.data.models.Settings;

public interface SettingsStorage {
    boolean saveTheme(Settings settings);
    Settings getTheme();

    boolean saveGpu(Settings settings);
    Settings getGpu();

    boolean savePercent(Settings settings);
    Settings getPercent();

    boolean saveSpeedFrameDetection(Settings settings);
    Settings getSpeedFrameDetection();
}
