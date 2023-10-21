package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.ImageDetected

interface RecognizeCoordinateRepository {
    fun recognise(imageDetected: ImageDetected): CoordinateClassification
}
