package com.ortin.gesturetranslator.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.ortin.gesturetranslator.data.models.Settings

class SharedPrefSettingsStorage(context: Context): SettingsStorage {
    companion object {
        private const val SHARED_PREFS_NAME: String = "settings"
        private const val KEY_THEME: String = "theme"
        private const val KEY_GPU: String = "gpu"
        private const val KEY_PERCENT: String = "percent"
        private const val KEY_SPEED_FRAME_DETECTION: String = "speed_frame_detection"
        private const val DEFAULT_THEME: Boolean = false
        private const val DEFAULT_GPU: Boolean = false
        private const val DEFAULT_PERCENT: Boolean = false
        private const val DEFAULT_SPEED_FRAME_DETECTION: Int = 10
    }
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveTheme(settings: Settings): Boolean {
        sharedPreferences.edit().putBoolean(KEY_THEME, settings.theme).apply()
        return true
    }

    override fun getTheme(): Settings {
        val settings = Settings()
        settings.theme = sharedPreferences.getBoolean(KEY_THEME, DEFAULT_THEME)
        return settings
    }

    override fun saveGpu(settings: Settings): Boolean {
        sharedPreferences.edit().putBoolean(KEY_GPU, settings.gpu).apply()
        return true
    }

    override fun getGpu(): Settings {
        val settings = Settings()
        settings.gpu = sharedPreferences.getBoolean(KEY_GPU, DEFAULT_GPU)
        return settings
    }

    override fun savePercent(settings: Settings): Boolean {
        sharedPreferences.edit().putBoolean(KEY_PERCENT, settings.percent).apply()
        return true
    }

    override fun getPercent(): Settings {
        val settings = Settings()
        settings.percent = sharedPreferences.getBoolean(KEY_PERCENT, DEFAULT_PERCENT)
        return settings
    }

    override fun saveSpeedFrameDetection(settings: Settings): Boolean {
        sharedPreferences.edit().putInt(KEY_SPEED_FRAME_DETECTION, settings.speedFrameDetection)
            .apply()
        return true
    }

    override fun getSpeedFrameDetection(): Settings {
        val settings = Settings()
        settings.speedFrameDetection =
            sharedPreferences.getInt(KEY_SPEED_FRAME_DETECTION, DEFAULT_SPEED_FRAME_DETECTION)
        return settings
    }

}