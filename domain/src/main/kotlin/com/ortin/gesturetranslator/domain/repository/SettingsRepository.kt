package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.models.SettingsDomain

interface SettingsRepository {
    fun saveTheme(settingsDomain: SettingsDomain): Boolean

    fun getTheme(): SettingsDomain

    fun saveGpu(settingsDomain: SettingsDomain): Boolean

    fun getGpu(): SettingsDomain        

    fun savePercent(settingsDomain: SettingsDomain): Boolean

    fun getPercent(): SettingsDomain

    fun saveSpeedFrameDetection(settingsDomain: SettingsDomain): Boolean

    fun getSpeedFrameDetection(): SettingsDomain
}
