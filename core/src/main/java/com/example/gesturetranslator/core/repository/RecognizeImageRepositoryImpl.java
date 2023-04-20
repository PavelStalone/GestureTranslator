package com.example.gesturetranslator.core.repository;

import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.TFLManager;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.listeners.TFLRecognizeListener;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TFLImage;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.models.TFLImageClasification;
import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.models.ImageClassifications;
import com.example.gesturetranslator.domain.repository.RecognizeImageRepository;

import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;

public class RecognizeImageRepositoryImpl implements RecognizeImageRepository {
    private TFLManager tflManager;

    public RecognizeImageRepositoryImpl(TFLManager tflManager) {
        this.tflManager = tflManager;
    }

    @Override
    public void recogniseImage(Image image) {
        tflManager.recogniseImage(mapToCoreImage(image));
    }

    @Override
    public void setRecogniseListener(RecognizeImageListener recognizeImageListener) {
        tflManager.setTflRecogniseListener(mapperToCoreListener(recognizeImageListener));
    }





    // Правила перевода для связи domain и core модулей

    private TFLImage mapToCoreImage(Image image) {
        ImageProcessor imageProcessor = new ImageProcessor.Builder().build();
        TensorImage tensorImage = imageProcessor.process(TensorImage.fromBitmap(image.getBitmap()));

        return new TFLImage(tensorImage, image.getRotaion());
    }

    private ImageClassifications mapToCoreImageClasification(TFLImageClasification tflImageClasification) {
        return new ImageClassifications(tflImageClasification.getLabel(), tflImageClasification.getPercent());
    }

    private TFLRecognizeListener mapperToCoreListener(RecognizeImageListener recognizeImageListener) {
        return new TFLRecognizeListener() {
            @Override
            public void recognise(TFLImageClasification tflImageClasification) {
                recognizeImageListener.recognise(mapToCoreImageClasification(tflImageClasification));
            }

            @Override
            public void error(Exception exception) {
                recognizeImageListener.error(exception);
            }
        };
    }
}
