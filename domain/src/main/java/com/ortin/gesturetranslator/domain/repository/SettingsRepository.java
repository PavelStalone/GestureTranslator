package com.ortin.gesturetranslator.domain.repository;

import com.ortin.gesturetranslator.domain.models.SettingsDomain;

public interface SettingsRepository {
    boolean saveTheme(SettingsDomain settingsDomain);
    SettingsDomain getTheme();

    boolean saveGpu(SettingsDomain settingsDomain);
    SettingsDomain getGpu();

    boolean savePercent(SettingsDomain settingsDomain);
    SettingsDomain getPercent();

    boolean saveSpeedFrameDetection(SettingsDomain settingsDomain);
    SettingsDomain getSpeedFrameDetection();
}
