package com.ortin.gesturetranslator.presentation;

import androidx.lifecycle.ViewModel;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;
import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeImageUseCase;

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
