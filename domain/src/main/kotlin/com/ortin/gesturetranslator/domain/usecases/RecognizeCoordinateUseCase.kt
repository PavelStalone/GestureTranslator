package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import javax.inject.Inject

class RecognizeCoordinateUseCase @Inject constructor(private val recognizeCoordinateRepository: RecognizeCoordinateRepository) {
    fun execute(imageDetected: ImageDetected): CoordinateClassification =
        recognizeCoordinateRepository.recognise(imageDetected)
}
