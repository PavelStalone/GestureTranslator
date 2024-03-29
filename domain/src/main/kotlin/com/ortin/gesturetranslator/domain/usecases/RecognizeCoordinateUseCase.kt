package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import javax.inject.Inject
import javax.inject.Singleton

interface RecognizeCoordinateUseCase : UseCase<ImageDetected, CoordinateClassification>

@Singleton
class RecognizeCoordinateUseCaseImpl @Inject constructor(
    private val recognizeCoordinateRepository: RecognizeCoordinateRepository
) : RecognizeCoordinateUseCase {
    override fun invoke(data: ImageDetected): CoordinateClassification =
        recognizeCoordinateRepository.recognise(data)
}
