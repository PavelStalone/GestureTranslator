package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository;

public class DetectHandUseCase {
    private final HandDetectionRepository handDetectionRepository;

    public DetectHandUseCase(HandDetectionRepository handDetectionRepository) {
        this.handDetectionRepository = handDetectionRepository;
    }

    public void execute(Image image) {
        handDetectionRepository.detectImage(image);
    }

    public void setOnDetectionHandListener(DetectionHandListener detectionHandListener) {
        handDetectionRepository.setDetectionHandListener(detectionHandListener);
    }
}
