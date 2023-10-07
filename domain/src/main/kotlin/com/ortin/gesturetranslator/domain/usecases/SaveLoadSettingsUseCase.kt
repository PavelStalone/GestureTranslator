package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.repository.SettingsRepository

class SaveLoadSettingsUseCase(private val settingsRepository: SettingsRepository) {
    fun setTheme(theme: Boolean): Boolean {
        val settingsDomain = SettingsDomain()
        settingsDomain.setTheme(theme)
        return settingsRepository.saveTheme(settingsDomain)
    }

    fun setGpu(gpu: Boolean): Boolean {
        val settingsDomain = SettingsDomain()
        settingsDomain.setGpu(gpu)
        return settingsRepository.saveGpu(settingsDomain)
    }

    fun setPercent(percent: Boolean): Boolean {
        val settingsDomain = SettingsDomain()
        settingsDomain.setPercent(percent)
        return settingsRepository.savePercent(settingsDomain)
    }

    fun setSpeedFrameDetection(speedFrameDetection: Int): Boolean {
        val settingsDomain = SettingsDomain()
        settingsDomain.setSpeedFrameDetection(speedFrameDetection)
        return settingsRepository.saveSpeedFrameDetection(settingsDomain)
    }

    fun getTheme(): Boolean {
        val settingsDomain = settingsRepository.getTheme()
        return settingsDomain.isTheme()
    }

    fun getGpu(): Boolean {
        val settingsDomain = settingsRepository.getGpu()
        return settingsDomain.isGpu()
    }

    fun getPercent(): Boolean {
        val settingsDomain = settingsRepository.getPercent()
        return settingsDomain.isPercent()
    }

    fun getSpeedFrameDetection(): Int {
        val settingsDomain = settingsRepository.getSpeedFrameDetection()
        return settingsDomain.getSpeedFrameDetection()
    }
}