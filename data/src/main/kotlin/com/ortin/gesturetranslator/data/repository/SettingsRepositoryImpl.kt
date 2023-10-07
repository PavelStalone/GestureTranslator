package com.ortin.gesturetranslator.data.repository

import com.ortin.gesturetranslator.data.models.Settings
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage) : SettingsRepository {
    override fun saveTheme(settingsDomain: SettingsDomain): Boolean =
            settingsStorage.saveTheme(settingsDomain.mapToData())

    override fun getTheme(): SettingsDomain = settingsStorage.getTheme().mapToDomain()

    override fun saveGpu(settingsDomain: SettingsDomain): Boolean =
            settingsStorage.saveGpu(settingsDomain.mapToData())

    override fun getGpu(): SettingsDomain = settingsStorage.getGpu().mapToDomain()

    override fun savePercent(settingsDomain: SettingsDomain): Boolean =
            settingsStorage.savePercent(settingsDomain.mapToData())

    override fun getPercent(): SettingsDomain = settingsStorage.getPercent().mapToDomain()

    override fun saveSpeedFrameDetection(settingsDomain: SettingsDomain): Boolean =
            settingsStorage.saveSpeedFrameDetection(settingsDomain.mapToData())

    override fun getSpeedFrameDetection(): SettingsDomain =
            settingsStorage.getSpeedFrameDetection().mapToDomain()

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
