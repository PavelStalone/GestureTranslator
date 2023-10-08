package com.ortin.gesturetranslator.domain.models

data class SettingsDomain(
    val theme: Boolean = false,
    val gpu: Boolean = false,
    val percent: Boolean = false,
    val speedFrameDetection: Int = 0
)
