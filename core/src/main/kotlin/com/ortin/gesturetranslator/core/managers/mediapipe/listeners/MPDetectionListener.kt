package com.ortin.gesturetranslator.core.managers.mediapipe.listeners

import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection

interface MPDetectionListener {
    fun detect(mpDetection: MPDetection?)
    fun error(exception: Exception)
}
