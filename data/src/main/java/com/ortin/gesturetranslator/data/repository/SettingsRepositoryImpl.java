package com.ortin.gesturetranslator.data.repository;

import com.ortin.gesturetranslator.data.models.Settings;
import com.ortin.gesturetranslator.data.storage.SettingsStorage;
import com.ortin.gesturetranslator.domain.models.SettingsDomain;
import com.ortin.gesturetranslator.domain.repository.SettingsRepository;

public class SettingsRepositoryImpl implements SettingsRepository {
    private final SettingsStorage settingsStorage;

    public SettingsRepositoryImpl(SettingsStorage settingsStorage){
        this.settingsStorage = settingsStorage;
    }

    @Override
    public boolean saveTheme(SettingsDomain settingsDomain) {
        return settingsStorage.saveTheme(mapToData(settingsDomain));
    }

    @Override
    public SettingsDomain getTheme() {
        return mapToDomain(settingsStorage.getTheme());
    }

    @Override
    public boolean saveGpu(SettingsDomain settingsDomain) {
        return settingsStorage.saveGpu(mapToData(settingsDomain));
    }

    @Override
    public SettingsDomain getGpu() {
        return mapToDomain(settingsStorage.getGpu());
    }

    @Override
    public boolean savePercent(SettingsDomain settingsDomain) {
        return settingsStorage.savePercent(mapToData(settingsDomain));
    }

    @Override
    public SettingsDomain getPercent() {
        return mapToDomain(settingsStorage.getPercent());
    }

    @Override
    public boolean saveSpeedFrameDetection(SettingsDomain settingsDomain) {
        return settingsStorage.saveSpeedFrameDetection(mapToData(settingsDomain));
    }

    @Override
    public SettingsDomain getSpeedFrameDetection() {
        return mapToDomain(settingsStorage.getSpeedFrameDetection());
    }

    private Settings mapToData(SettingsDomain settingsDomain){
        Settings settings = new Settings();
        settings.setTheme(settingsDomain.isTheme())
                .setGpu(settingsDomain.isGpu())
                .setPercent(settingsDomain.isPercent())
                .setSpeedFrameDetection(settingsDomain.getSpeedFrameDetection());
        return settings;
    }

    private SettingsDomain mapToDomain(Settings settings){
        SettingsDomain settingsDomain = new SettingsDomain();
        settingsDomain.setTheme(settings.isTheme())
                .setGpu(settings.isGpu())
                .setPercent(settings.isPercent())
                .setSpeedFrameDetection(settings.getSpeedFrameDetection());
        return settingsDomain;
    }
}
