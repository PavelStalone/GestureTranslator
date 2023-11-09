package com.ortin.gesturetranslator.domain.managers

import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsManager @Inject constructor(private val settingsRepository: SettingsRepository) {
    fun saveSettings(settingsDomain: SettingsDomain): Boolean =
        settingsRepository.saveSettings(settingsDomain)

    fun getSettings(): SettingsDomain = settingsRepository.getSettings()
}
