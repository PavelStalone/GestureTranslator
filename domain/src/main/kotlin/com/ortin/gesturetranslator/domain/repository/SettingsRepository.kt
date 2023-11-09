package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.models.SettingsDomain

interface SettingsRepository {
    // TODO Сделать функции suspend
    fun saveSettings(settingsDomain: SettingsDomain) : Boolean
    fun getSettings() : SettingsDomain
}
