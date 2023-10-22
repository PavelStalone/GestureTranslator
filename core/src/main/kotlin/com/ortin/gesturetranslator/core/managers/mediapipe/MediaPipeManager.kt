package com.ortin.gesturetranslator.core.managers.mediapipe

import android.graphics.Bitmap
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoInput
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel

interface MediaPipeManager {
    fun detectImage(image: Bitmap): MPImageDetection?
    fun detectVideoFile(videoFile: MPVideoInput): MPVideoDetection?
    fun detectLiveStream(image: Bitmap)
    fun setSettingsModel(settingsModel: SettingsModel)
    fun setMPDetectionListener(mpDetectionListener: MPDetectionListener)
}
