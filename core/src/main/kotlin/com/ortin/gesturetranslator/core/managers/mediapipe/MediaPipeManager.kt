package com.ortin.gesturetranslator.core.managers.mediapipe

import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel
import kotlinx.coroutines.flow.StateFlow

interface MediaPipeManager {
    fun detectImage(mpImageInput: MPImageInput)
    fun setMPDetectionListener(mpDetectionListener: MPDetectionListener, settingsModel: SettingsModel)
}
