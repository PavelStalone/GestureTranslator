package com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.listeners;

import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TFLImageClasification;

public interface TFLRecognizeListener {
    void recognise(TFLImageClasification tflImageClasification);
    void error(Exception exception);
}
