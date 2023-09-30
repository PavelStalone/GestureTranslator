package com.ortin.gesturetranslator.data.repository

import com.ortin.gesturetranslator.data.models.Settings
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage) : SettingsRepository {
    override fun saveTheme(settingsDomain: SettingsDomain): Boolean =
        settingsStorage.saveTheme(mapToData(settingsDomain))

    override fun getTheme(): SettingsDomain = mapToDomain(settingsStorage.getTheme())

    override fun saveGpu(settingsDomain: SettingsDomain): Boolean =
        settingsStorage.saveGpu(mapToData(settingsDomain))

    override fun getGpu(): SettingsDomain = mapToDomain(settingsStorage.getGpu())

    override fun savePercent(settingsDomain: SettingsDomain): Boolean =
        settingsStorage.savePercent(mapToData(settingsDomain))

    override fun getPercent(): SettingsDomain = mapToDomain(settingsStorage.getPercent())

    override fun saveSpeedFrameDetection(settingsDomain: SettingsDomain): Boolean =
        settingsStorage.saveSpeedFrameDetection(mapToData(settingsDomain))

    override fun getSpeedFrameDetection(): SettingsDomain =
        mapToDomain(settingsStorage.getSpeedFrameDetection())

    private fun mapToData(settingsDomain: SettingsDomain): Settings {
        val settings = Settings()
        settings.theme = settingsDomain.isTheme
        settings.percent = settingsDomain.isPercent
        settings.speedFrameDetection = settingsDomain.speedFrameDetection
        settings.gpu = settingsDomain.isGpu
        return settings
    }

    private fun mapToDomain(settings: Settings): SettingsDomain {
        val settingsDomain = SettingsDomain()
        settingsDomain.isTheme = settings.theme
        settingsDomain.isGpu = settings.gpu
        settingsDomain.isPercent = settings.percent
        settingsDomain.speedFrameDetection = settings.speedFrameDetection
        return settingsDomain
    }
}
