package com.ortin.gesturetranslator.domain.repository;

import androidx.annotation.NonNull;

import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe;

public interface HandDetectionRepository {
    void detectImage(@NonNull Image image);

    void setDetectionHandListener(@NonNull DetectionHandListener detectionHandListener, @NonNull SettingsMediaPipe settingsMediaPipe);
}
