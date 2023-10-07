package com.ortin.gesturetranslator.domain.listeners

import com.ortin.gesturetranslator.domain.models.HandDetected

interface DetectionHandListener {
    fun detect(handDetected: HandDetected)

    fun error(exception: Exception)
}