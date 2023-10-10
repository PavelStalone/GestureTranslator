package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository

class SaveLoadSettingsUseCase(private val settingsRepository: SettingsRepository) {
    fun setTheme(theme: Boolean): Boolean {
        val settingsDomain = SettingsDomain(theme = theme)
        return settingsRepository.saveTheme(settingsDomain)
    }

    fun setGpu(gpu: Boolean): Boolean {
        val settingsDomain = SettingsDomain(gpu = gpu)
        return settingsRepository.saveGpu(settingsDomain)
    }

    fun setPercent(percent: Boolean): Boolean {
        val settingsDomain = SettingsDomain(percent = percent)
        return settingsRepository.savePercent(settingsDomain)
    }

    fun setSpeedFrameDetection(speedFrameDetection: Int): Boolean {
        val settingsDomain = SettingsDomain(speedFrameDetection = speedFrameDetection)
        return settingsRepository.saveSpeedFrameDetection(settingsDomain)
    }

    fun getTheme(): Boolean =
        settingsRepository.getTheme().theme

    fun getGpu(): Boolean =
        settingsRepository.getGpu().gpu

    fun getPercent(): Boolean =
        settingsRepository.getPercent().percent

    fun getSpeedFrameDetection(): Int =
        settingsRepository.getSpeedFrameDetection().speedFrameDetection
}
