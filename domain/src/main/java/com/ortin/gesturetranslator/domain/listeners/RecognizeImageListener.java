package com.ortin.gesturetranslator.domain.listeners;

import com.ortin.gesturetranslator.domain.models.ImageClassification;

public interface RecognizeImageListener {
    void recognise(ImageClassification imageClassification);
    void error(Exception exception);
}
