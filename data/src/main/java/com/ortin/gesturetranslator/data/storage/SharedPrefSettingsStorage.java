package com.ortin.gesturetranslator.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.ortin.gesturetranslator.data.models.Settings;

public class SharedPrefSettingsStorage implements SettingsStorage {
    private static final String SHARED_PREFS_NAME = "settings";
    private static final String KEY_THEME = "theme";
    private static final String KEY_GPU = "gpu";
    private static final String KEY_PERCENT = "percent";
    private static final String KEY_SPEED_FRAME_DETECTION = "speed_frame_detection";

    private static final boolean DEFAULT_THEME = false;
    private static final boolean DEFAULT_GPU = false;
    private static final boolean DEFAULT_PERCENT = false;
    private static final int DEFAULT_SPEED_FRAME_DETECTION = 10;

    private final SharedPreferences sharedPreferences;

    public SharedPrefSettingsStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveTheme(Settings settings) {
        sharedPreferences.edit().putBoolean(KEY_THEME, settings.isTheme()).apply();
        return true;
    }

    @Override
    public Settings getTheme() {
        Settings settings = new Settings();
        settings.setTheme(sharedPreferences.getBoolean(KEY_THEME, DEFAULT_THEME));
        return settings;
    }

    @Override
    public boolean saveGpu(Settings settings) {
        sharedPreferences.edit().putBoolean(KEY_GPU, settings.isGpu()).apply();
        return true;
    }

    @Override
    public Settings getGpu() {
        Settings settings = new Settings();
        settings.setGpu(sharedPreferences.getBoolean(KEY_GPU, DEFAULT_GPU));
        return settings;
    }

    @Override
    public boolean savePercent(Settings settings) {
        sharedPreferences.edit().putBoolean(KEY_PERCENT, settings.isPercent()).apply();
        return true;
    }

    @Override
    public Settings getPercent() {
        Settings settings = new Settings();
        settings.setPercent(sharedPreferences.getBoolean(KEY_PERCENT, DEFAULT_PERCENT));
        return settings;
    }

    @Override
    public boolean saveSpeedFrameDetection(Settings settings) {
        sharedPreferences.edit().putInt(KEY_SPEED_FRAME_DETECTION, settings.getSpeedFrameDetection()).apply();
        return true;
    }

    @Override
    public Settings getSpeedFrameDetection() {
        Settings settings = new Settings();
        settings.setSpeedFrameDetection(sharedPreferences.getInt(KEY_SPEED_FRAME_DETECTION, DEFAULT_SPEED_FRAME_DETECTION));
        return settings;
    }
}
