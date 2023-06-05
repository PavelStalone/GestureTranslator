package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.models.SettingsDomain;
import com.ortin.gesturetranslator.domain.repository.SettingsRepository;

public class SaveLoadSettingsUseCase {

    private final SettingsRepository settingsRepository;

    public SaveLoadSettingsUseCase(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public boolean setTheme(boolean theme) {
        SettingsDomain settingsDomain = new SettingsDomain();
        settingsDomain.setTheme(theme);
        return settingsRepository.saveTheme(settingsDomain);
    }

    public boolean setGpu(boolean gpu) {
        SettingsDomain settingsDomain = new SettingsDomain();
        settingsDomain.setGpu(gpu);
        return settingsRepository.saveGpu(settingsDomain);
    }

    public boolean setPercent(boolean percent) {
        SettingsDomain settingsDomain = new SettingsDomain();
        settingsDomain.setPercent(percent);
        return settingsRepository.savePercent(settingsDomain);
    }

    public boolean setSpeedFrameDetection(int speedFrameDetection) {
        SettingsDomain settingsDomain = new SettingsDomain();
        settingsDomain.setSpeedFrameDetection(speedFrameDetection);
        return settingsRepository.saveSpeedFrameDetection(settingsDomain);
    }

    public boolean getTheme() {
        SettingsDomain settingsDomain = settingsRepository.getTheme();
        return settingsDomain.isTheme();
    }

    public boolean getGpu() {
        SettingsDomain settingsDomain = settingsRepository.getGpu();
        return settingsDomain.isGpu();
    }

    public boolean getPercent() {
        SettingsDomain settingsDomain = settingsRepository.getPercent();
        return settingsDomain.isPercent();
    }

    public int getSpeedFrameDetection() {
        SettingsDomain settingsDomain = settingsRepository.getSpeedFrameDetection();
        return settingsDomain.getSpeedFrameDetection();
    }

}
