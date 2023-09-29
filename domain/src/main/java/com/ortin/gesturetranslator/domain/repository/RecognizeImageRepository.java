package com.ortin.gesturetranslator.domain.repository;

import androidx.annotation.NonNull;

import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.ortin.gesturetranslator.domain.models.Image;

public interface RecognizeImageRepository {
    void recogniseImage(@NonNull Image image);
    void setRecogniseListener(@NonNull RecognizeImageListener recognizeImageListener);
}
