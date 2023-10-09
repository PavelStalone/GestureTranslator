package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.HandDetected
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository

class RecognizeCoordinateUseCase(private val recognizeCoordinateRepository: RecognizeCoordinateRepository) {
    fun execute(handDetected: HandDetected): CoordinateClassification =
        recognizeCoordinateRepository.recognise(handDetected)
}
