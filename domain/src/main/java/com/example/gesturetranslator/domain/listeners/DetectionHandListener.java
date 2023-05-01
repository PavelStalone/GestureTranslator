package com.example.gesturetranslator.domain.listeners;

import com.example.gesturetranslator.domain.models.HandDetected;

public interface DetectionHandListener {
    void detect(HandDetected handDetected);

    void error(Exception exception);
}
