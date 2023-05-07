package com.ortin.gesturetranslator.domain.repository;

import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener;
import com.ortin.gesturetranslator.domain.models.Image;

public interface HandDetectionRepository {
    void detectImage(Image image);

    void setDetectionHandListener(DetectionHandListener detectionHandListener);
}
