package com.example.gesturetranslator.core.managers.tensor_flow_lite_manager;

import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.listeners.TFLRecognizeListener;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TFLImage;

public interface TFLManager {
    void recogniseImage(TFLImage tflImage);
    void setTflRecogniseListener(TFLRecognizeListener tflRecognizeListener);
}
