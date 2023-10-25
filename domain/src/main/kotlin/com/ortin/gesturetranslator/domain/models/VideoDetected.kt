package com.ortin.gesturetranslator.domain.models

data class VideoDetected(
    val results: List<ImageDetected?>,
    val inferenceTime: Long,
    val inputImageHeight: Int,
    val inputImageWidth: Int
)
