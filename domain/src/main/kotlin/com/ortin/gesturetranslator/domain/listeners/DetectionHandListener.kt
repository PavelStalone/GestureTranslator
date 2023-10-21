package com.ortin.gesturetranslator.domain.listeners

import com.ortin.gesturetranslator.domain.models.ImageDetected

interface DetectionHandListener {
    fun detect(imageDetected: ImageDetected?)

    fun error(exception: Exception)
}
