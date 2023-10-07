package com.ortin.gesturetranslator.domain.usecases;

import android.graphics.Bitmap;
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener;
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository;

public class DetectHandUseCase {
    private final HandDetectionRepository handDetectionRepository;

    public DetectHandUseCase(HandDetectionRepository handDetectionRepository) {
        this.handDetectionRepository = handDetectionRepository;
    }

    public void execute(Bitmap image) {
        handDetectionRepository.detectLiveStream(image);
    }

    public void setOnDetectionHandListener(DetectionHandListener detectionHandListener) {
        handDetectionRepository.setMPDetectionListener(detectionHandListener);
    }
}
