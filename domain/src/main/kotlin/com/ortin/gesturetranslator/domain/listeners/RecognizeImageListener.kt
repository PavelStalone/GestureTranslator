package com.ortin.gesturetranslator.domain.listeners

import com.ortin.gesturetranslator.domain.models.ImageClassification

interface RecognizeImageListener {
    fun recognise(imageClassification: ImageClassification)

    fun error(exception: Exception)
}
