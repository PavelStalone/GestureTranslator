package com.example.gesturetranslator.domain.repository;

import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.models.Image;

public interface RecognizeImageRepository {
    void recogniseImage(Image image);
    void setRecogniseListener(RecognizeImageListener recognizeImageListener);
}
