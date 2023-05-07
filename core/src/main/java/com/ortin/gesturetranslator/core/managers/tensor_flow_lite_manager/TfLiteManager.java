package com.ortin.gesturetranslator.core.managers.tensor_flow_lite_manager;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite_manager.listeners.TfLiteRecognizeListener;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TfLiteImage;

public interface TfLiteManager {
    void recogniseImage(TfLiteImage tfLiteImage);
    void setTflRecogniseListener(TfLiteRecognizeListener tfLiteRecognizeListener);
}
