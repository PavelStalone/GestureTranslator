package com.ortin.gesturetranslator.core.managers.mediapipe.models

import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult

data class MPVideoDetection(
    val results: List<HandLandmarkerResult>,
    val inferenceTime: Long,
    val inputImageHeight: Int,
    val inputImageWidth: Int
)
