package com.ortin.gesturetranslator.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.ortin.gesturetranslator.data.models.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefSettingsStorage @Inject constructor(@ApplicationContext context: Context) :
    SettingsStorage {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveSettings(settings: Settings): Boolean {
        sharedPreferences.edit().putBoolean(KEY_THEME, settings.theme).apply()
        sharedPreferences.edit().putBoolean(KEY_GPU, settings.gpu).apply()
        sharedPreferences.edit().putBoolean(KEY_PERCENT, settings.percent).apply()
        sharedPreferences.edit().putInt(KEY_SPEED_FRAME_DETECTION, settings.speedFrameDetection)
            .apply()
        return true
    }

    override fun getSettings(): Settings =
        Settings(
            theme = sharedPreferences.getBoolean(KEY_THEME, DEFAULT_THEME),
            gpu = sharedPreferences.getBoolean(KEY_GPU, DEFAULT_GPU),
            percent = sharedPreferences.getBoolean(KEY_PERCENT, DEFAULT_PERCENT),
            speedFrameDetection = sharedPreferences.getInt(
                KEY_SPEED_FRAME_DETECTION,
                DEFAULT_SPEED_FRAME_DETECTION
            )
        )

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
}
