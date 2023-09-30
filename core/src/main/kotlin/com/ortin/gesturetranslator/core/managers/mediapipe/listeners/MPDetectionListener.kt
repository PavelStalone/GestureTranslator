package com.ortin.gesturetranslator.core.managers.mediapipe.listeners

import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection
import java.lang.Exception

interface MPDetectionListener {
    fun detect(mpDetection: MPDetection?)
    fun error(exception: Exception)
}
