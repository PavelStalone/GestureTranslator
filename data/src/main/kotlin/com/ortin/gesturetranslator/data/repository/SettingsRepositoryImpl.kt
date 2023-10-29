package com.ortin.gesturetranslator.data.repository

import com.ortin.gesturetranslator.data.models.Settings
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsStorage: SettingsStorage
) : SettingsRepository {
    override fun saveSettings(settingsDomain: SettingsDomain): Boolean =
        settingsStorage.saveSettings(settingsDomain.mapToData())

    override fun getSettings(): SettingsDomain =
        settingsStorage.getSettings().mapToDomain()

    private fun SettingsDomain.mapToData(): Settings =
        Settings(
            theme = this.theme,
            percent = this.percent,
            speedFrameDetection = this.speedFrameDetection,
            gpu = this.gpu
        )

    private fun Settings.mapToDomain(): SettingsDomain =
        SettingsDomain(
            theme = this.theme,
            gpu = this.gpu, percent = this.percent,
            speedFrameDetection = this.speedFrameDetection
        )
}
