package com.ortin.gesturetranslator.domain.listeners;

import com.ortin.gesturetranslator.domain.models.ImageClassifications;

public interface RecognizeImageListener {
    void recognise(ImageClassifications imageClassifications);
    void error(Exception exception);
}
