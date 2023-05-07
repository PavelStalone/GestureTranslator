package com.ortin.gesturetranslator.core.managers.tensor_flow_lite;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.listeners.TfLiteRecognizeListener;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImage;

public interface TfLiteManager {
    void recogniseImage(TfLiteImage tfLiteImage);
    void setTflRecogniseListener(TfLiteRecognizeListener tfLiteRecognizeListener);
}
