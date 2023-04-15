package com.example.gesturetranslator.presentation;

import androidx.lifecycle.ViewModel;

import com.example.gesturetranslator.domain.listeners.LoadImagesListener;
import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.example.gesturetranslator.domain.usecases.RecognizeImageUseCase;

public class MainViewModel extends ViewModel {

    private LoadImageUseCase loadImageUseCase;
    private RecognizeImageUseCase recognizeImageUseCase;

    public MainViewModel(LoadImageUseCase loadImageUseCase, RecognizeImageUseCase recognizeImageUseCase) {
        this.loadImageUseCase = loadImageUseCase;
        this.recognizeImageUseCase = recognizeImageUseCase;
    }

    public void setLoadImagesListener(LoadImagesListener loadImagesListener){

    }

    public void setRecognizeImageListener(RecognizeImageListener recognizeImageListener){

    }

}
