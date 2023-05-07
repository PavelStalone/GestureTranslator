package com.ortin.gesturetranslator.domain.repository;

import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.ortin.gesturetranslator.domain.models.Image;

public interface RecognizeImageRepository {
    void recogniseImage(Image image);
    void setRecogniseListener(RecognizeImageListener recognizeImageListener);
}
