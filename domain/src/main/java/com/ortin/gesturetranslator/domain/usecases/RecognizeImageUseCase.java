package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository;

public class RecognizeImageUseCase {
    private RecognizeImageRepository recognizeImageRepository;

    public RecognizeImageUseCase(RecognizeImageRepository recognizeImageRepository) {
        this.recognizeImageRepository = recognizeImageRepository;
    }

    public void execute(Image image){
        recognizeImageRepository.recogniseImage(image);
    }

    public void setOnRecogniseListener(RecognizeImageListener recognizeImageListener){
        recognizeImageRepository.setRecogniseListener(recognizeImageListener);
    }
}
