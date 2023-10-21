package com.ortin.gesturetranslator.data.models

data class Settings(
    val theme: Boolean = true,
    val gpu: Boolean = true,
    val percent: Boolean = true,
    val speedFrameDetection: Int = 30
)
