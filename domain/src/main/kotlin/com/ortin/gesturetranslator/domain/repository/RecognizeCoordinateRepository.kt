package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.HandDetected

interface RecognizeCoordinateRepository {
    fun recognise(handDetected: HandDetected): CoordinateClassification
}
