package com.ortin.gesturetranslator.core.managers.tensor_flow_lite.listeners;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImageClasification;

public interface TfLiteRecognizeListener {
    void recognise(TfLiteImageClasification tfLiteImageClasification);
    void error(Exception exception);
}
