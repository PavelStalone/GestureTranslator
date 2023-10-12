package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener
import com.ortin.gesturetranslator.domain.models.Image

interface RecognizeImageRepository {
    fun recogniseImage(image: Image)

    fun setRecogniseListener(recognizeImageListener: RecognizeImageListener)
}
