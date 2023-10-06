package com.ortin.gesturetranslator.core.managers.mediapipe.listeners

import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection

interface MPDetectionListener {
    fun onResults(mpDetection: MPDetection?)
    fun onError(exception: Exception)
}
