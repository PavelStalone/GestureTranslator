package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.Image

interface HandDetectionRepository {
    fun detectImage(image: Image?)

    fun setDetectionHandListener(detectionHandListener: DetectionHandListener?)
}