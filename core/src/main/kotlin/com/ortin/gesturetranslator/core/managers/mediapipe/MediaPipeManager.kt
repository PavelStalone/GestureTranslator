package com.ortin.gesturetranslator.core.managers.mediapipe

import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput

interface MediaPipeManager {
    fun detectImage(mpImageInput: MPImageInput)
    fun setMPDetectionListener(mpDetectionListener: MPDetectionListener)
}
