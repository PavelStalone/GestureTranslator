package com.ortin.gesturetranslator.core.managers.tensor_flow_lite.listeners;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImageClassification;

public interface TfLiteRecognizeListener {
    void recognise(TfLiteImageClassification tfLiteImageClassification);
    void error(Exception exception);
}
