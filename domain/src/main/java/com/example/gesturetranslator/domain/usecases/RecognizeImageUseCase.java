package com.example.gesturetranslator.domain.usecases;

import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.repository.RecognizeImageRepository;

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
