package com.ortin.gesturetranslator.core.managers.tensor_flow_lite_manager.listeners;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TfLiteImageClasification;

public interface TfLiteRecognizeListener {
    void recognise(TfLiteImageClasification tfLiteImageClasification);
    void error(Exception exception);
}
