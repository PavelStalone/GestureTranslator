package com.ortin.gesturetranslator.domain.listeners;

import com.ortin.gesturetranslator.domain.models.ImageDetected;

public interface DetectionHandListener {
    void detect(ImageDetected imageDetected);

    void error(Exception exception);
}
