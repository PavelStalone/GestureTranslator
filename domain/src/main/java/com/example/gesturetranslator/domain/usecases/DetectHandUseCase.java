package com.example.gesturetranslator.domain.usecases;

import com.example.gesturetranslator.domain.listeners.DetectionHandListener;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.repository.HandDetectionRepository;

public class DetectHandUseCase {
    private HandDetectionRepository handDetectionRepository;

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
