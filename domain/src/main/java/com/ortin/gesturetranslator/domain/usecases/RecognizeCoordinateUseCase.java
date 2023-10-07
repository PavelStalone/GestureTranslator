package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.ImageDetected;
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository;

public class RecognizeCoordinateUseCase {
    private final RecognizeCoordinateRepository recognizeCoordinateRepository;

    public RecognizeCoordinateUseCase(RecognizeCoordinateRepository recognizeCoordinateRepository) {
        this.recognizeCoordinateRepository = recognizeCoordinateRepository;
    }

    public CoordinateClassification execute(ImageDetected imageDetected) {
        return recognizeCoordinateRepository.recognise(imageDetected);
    }
}
