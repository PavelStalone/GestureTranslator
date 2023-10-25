package com.ortin.gesturetranslator.core.managers.mediapipe.models

import com.google.mediapipe.tasks.vision.core.RunningMode
import com.ortin.gesturetranslator.core.managers.mediapipe.HandLandmarkerHelper

data class SettingsModel(
    val minHandDetectionConfidence: Float = HandLandmarkerHelper.DEFAULT_HAND_DETECTION_CONFIDENCE,
    val minHandTrackingConfidence: Float = HandLandmarkerHelper.DEFAULT_HAND_TRACKING_CONFIDENCE,
    val minHandPresenceConfidence: Float = HandLandmarkerHelper.DEFAULT_HAND_PRESENCE_CONFIDENCE,
    val maxNumHands: Int = HandLandmarkerHelper.DEFAULT_NUM_HANDS,
    val currentDelegate: Int = HandLandmarkerHelper.DELEGATE_CPU,
    val runningMode: RunningMode = RunningMode.LIVE_STREAM
)
