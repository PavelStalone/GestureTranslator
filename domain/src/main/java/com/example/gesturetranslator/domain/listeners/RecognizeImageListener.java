package com.example.gesturetranslator.domain.listeners;

import com.example.gesturetranslator.domain.models.ImageClassifications;

public interface RecognizeImageListener {
    void recognise(ImageClassifications imageClassifications);
    void error(Exception exception);
}
