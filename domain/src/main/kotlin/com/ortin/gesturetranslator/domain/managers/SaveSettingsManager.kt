package com.ortin.gesturetranslator.domain.managers

import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveSettingsManager @Inject constructor(private val saveSettingsRepository: SettingsRepository) {
    fun saveSettings(settingsDomain: SettingsDomain): Boolean =
        saveSettingsRepository.saveSettings(settingsDomain)

    fun getSettings(): SettingsDomain =
        saveSettingsRepository.getSettings()
}
