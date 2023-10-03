package com.ortin.gesturetranslator.data.models

data class Settings(
    var theme: Boolean = true,
    var gpu: Boolean = true,
    var percent: Boolean = true,
    var speedFrameDetection: Int = 30
)
