package com.ortin.gesturetranslator.core.managers.mediapipe.listeners

import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageDetection

interface MPDetectionListener {
    fun onResults(mpImageDetection: MPImageDetection?)
    fun onError(exception: Exception)
}
