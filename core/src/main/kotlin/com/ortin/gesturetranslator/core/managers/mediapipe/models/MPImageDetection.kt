package com.ortin.gesturetranslator.core.managers.mediapipe.models

import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult

data class MPImageDetection(
    val result: HandLandmarkerResult,
    val inferenceTime: Long,
    val inputImageHeight: Int,
    val inputImageWidth: Int
)
