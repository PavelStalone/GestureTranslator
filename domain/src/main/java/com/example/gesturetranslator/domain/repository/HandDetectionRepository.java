package com.example.gesturetranslator.domain.repository;

import com.example.gesturetranslator.domain.listeners.DetectionHandListener;
import com.example.gesturetranslator.domain.models.Image;

public interface HandDetectionRepository {
    void detectImage(Image image);

    void setDetectionHandListener(DetectionHandListener detectionHandListener);
}
