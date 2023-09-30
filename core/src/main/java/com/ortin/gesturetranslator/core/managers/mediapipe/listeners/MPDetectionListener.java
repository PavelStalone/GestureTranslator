package com.ortin.gesturetranslator.core.managers.mediapipe.listeners;

import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection;

import io.reactivex.rxjava3.annotations.NonNull;

public interface MPDetectionListener {
    void detect(MPDetection mpDetection);

    void error(Exception exception);
}
