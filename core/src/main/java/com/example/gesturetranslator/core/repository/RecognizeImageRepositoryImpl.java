package com.example.gesturetranslator.core.repository;

import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.TfLiteManager;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.listeners.TfLiteRecognizeListener;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TfLiteImage;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TfLiteImageClasification;
import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.models.ImageClassifications;
import com.example.gesturetranslator.domain.repository.RecognizeImageRepository;

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
        tfLiteManager.setTflRecogniseListener(mapperToCoreListener(recognizeImageListener));
    }





    // Правила перевода для связи domain и core модулей

    private TfLiteImage mapToCoreImage(Image image) {
        ImageProcessor imageProcessor = new ImageProcessor.Builder().build();
        TensorImage tensorImage = imageProcessor.process(TensorImage.fromBitmap(image.getBitmap()));

        return new TfLiteImage(tensorImage, image.getRotaion());
    }

    private ImageClassifications mapToCoreImageClasification(TfLiteImageClasification tfLiteImageClasification) {
        return new ImageClassifications(tfLiteImageClasification.getLabel(), tfLiteImageClasification.getPercent());
    }

    private TfLiteRecognizeListener mapperToCoreListener(RecognizeImageListener recognizeImageListener) {
        return new TfLiteRecognizeListener() {
            @Override
            public void recognise(TfLiteImageClasification tfLiteImageClasification) {
                recognizeImageListener.recognise(mapToCoreImageClasification(tfLiteImageClasification));
            }

            @Override
            public void error(Exception exception) {
                recognizeImageListener.error(exception);
            }
        };
    }
}
