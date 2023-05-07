package com.ortin.gesturetranslator.domain.listeners;

import com.ortin.gesturetranslator.domain.models.HandDetected;

public interface DetectionHandListener {
    void detect(HandDetected handDetected);

    void error(Exception exception);
}
