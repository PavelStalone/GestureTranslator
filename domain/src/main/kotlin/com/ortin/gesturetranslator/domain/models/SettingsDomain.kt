package com.ortin.gesturetranslator.domain.models

class SettingsDomain(){
    private var theme: Boolean = false

    private var gpu: Boolean = false

    private var percent: Boolean = false

    private var speedFrameDetection: Int = 0
    fun setTheme(theme: Boolean): SettingsDomain? {
        this.theme = theme
        return this
    }

    fun setGpu(gpu: Boolean): SettingsDomain? {
        this.gpu = gpu
        return this
    }

    fun setPercent(percent: Boolean): SettingsDomain? {
        this.percent = percent
        return this
    }

    fun setSpeedFrameDetection(speedFrameDetection: Int): SettingsDomain? {
        this.speedFrameDetection = speedFrameDetection
        return this
    }

    fun isTheme(): Boolean {
        return theme
    }

    fun isGpu(): Boolean {
        return gpu
    }

    fun isPercent(): Boolean {
        return percent
    }

    fun getSpeedFrameDetection(): Int {
        return speedFrameDetection
    }
}