package com.ortin.gesturetranslator.core.repository;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.TfLiteManager;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.listeners.TfLiteRecognizeListener;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImage;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImageClassification;
import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.models.ImageClassification;
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository;

import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;

public class RecognizeImageRepositoryImpl implements RecognizeImageRepository {
    private TfLiteManager tfLiteManager;

    public RecognizeImageRepositoryImpl(TfLiteManager tfLiteManager) {
        this.tfLiteManager = tfLiteManager;
    }

    @Override
    public void recogniseImage(Image image) {
        tfLiteManager.recogniseImage(mapToCoreImage(image));
    }

    @Override
    public void setRecogniseListener(RecognizeImageListener recognizeImageListener) {
        tfLiteManager.setTflRecogniseListener(mapToCoreListener(recognizeImageListener));
    }


    // Правила перевода для связи domain и core модулей

    private TfLiteImage mapToCoreImage(Image image) {
        ImageProcessor imageProcessor = new ImageProcessor.Builder().build();
        TensorImage tensorImage = imageProcessor.process(TensorImage.fromBitmap(image.getBitmap()));

        return new TfLiteImage(tensorImage, image.getRotation());
    }

    private ImageClassification mapToCoreImageClassification(TfLiteImageClassification tfLiteImageClassification) {
        return new ImageClassification(tfLiteImageClassification.getLabel(), tfLiteImageClassification.getPercent());
    }

    private TfLiteRecognizeListener mapToCoreListener(RecognizeImageListener recognizeImageListener) {
        return new TfLiteRecognizeListener() {
            @Override
            public void recognise(TfLiteImageClassification tfLiteImageClassification) {
                recognizeImageListener.recognise(mapToCoreImageClassification(tfLiteImageClassification));
            }

            @Override
            public void error(Exception exception) {
                recognizeImageListener.error(exception);
            }
        };
    }
}
