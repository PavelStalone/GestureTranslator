package com.ortin.gesturetranslator.domain.models

data class SettingsMediaPipe(
    val minHandDetectionConfidence: Float = 0.5f,
    val minHandTrackingConfidence: Float = 0.5f,
    val minHandPresenceConfidence: Float = 0.5f,
    val maxNumHands: Int = 1,
    val currentDelegate: Delegation = Delegation.DELEGATE_CPU,
    val runningMode: InputMode = InputMode.LIVE_STREAM
){
    enum class Delegation{
        DELEGATE_CPU,
        DELEGATE_GPU
    }

    enum class InputMode{
        LIVE_STREAM,
        IMAGE,
        VIDEO
    }
}
