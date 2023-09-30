package com.ortin.gesturetranslator.data.storage

import com.ortin.gesturetranslator.data.models.Settings

interface SettingsStorage {
    fun saveTheme(settings: Settings): Boolean
    fun getTheme(): Settings

    fun saveGpu(settings: Settings): Boolean
    fun getGpu(): Settings

    fun savePercent(settings: Settings): Boolean
    fun getPercent(): Settings

    fun saveSpeedFrameDetection(settings: Settings): Boolean
    fun getSpeedFrameDetection(): Settings
}