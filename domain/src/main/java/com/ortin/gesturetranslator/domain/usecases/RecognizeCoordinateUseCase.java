package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.HandDetected;
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository;

public class RecognizeCoordinateUseCase {
    private final RecognizeCoordinateRepository recognizeCoordinateRepository;

    public RecognizeCoordinateUseCase(RecognizeCoordinateRepository recognizeCoordinateRepository) {
        this.recognizeCoordinateRepository = recognizeCoordinateRepository;
    }

    public CoordinateClassification execute(HandDetected handDetected) {
        return recognizeCoordinateRepository.recognise(handDetected);
    }
}
