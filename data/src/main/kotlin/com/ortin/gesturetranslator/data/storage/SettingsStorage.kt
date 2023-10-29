package com.ortin.gesturetranslator.data.storage

import com.ortin.gesturetranslator.data.models.Settings

interface SettingsStorage {
    fun saveSettings(settings: Settings): Boolean
    fun getSettings(): Settings
}
