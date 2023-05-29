package com.ortin.gesturetranslator.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;
import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase;
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeImageUseCase;
import com.ortin.gesturetranslator.domain.usecases.WordCompileUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private LoadImageUseCase loadImageUseCase;
    private RecognizeImageUseCase recognizeImageUseCase;
    private WordCompileUseCase wordCompileUseCase;
    private DetectHandUseCase detectHandUseCase;
    private RecognizeCoordinateUseCase recognizeCoordinateUseCase;

    private final MutableLiveData<String> liveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(LoadImageUseCase loadImageUseCase, RecognizeImageUseCase recognizeImageUseCase, WordCompileUseCase wordCompileUseCase, DetectHandUseCase detectHandUseCase, RecognizeCoordinateUseCase recognizeCoordinateUseCase) {
        this.loadImageUseCase = loadImageUseCase;
        this.recognizeImageUseCase = recognizeImageUseCase;
        this.wordCompileUseCase = wordCompileUseCase;
        this.detectHandUseCase = detectHandUseCase;
        this.recognizeCoordinateUseCase = recognizeCoordinateUseCase;
    }

    public LiveData<String> getLiveData(){
        return liveData;
    }

    public void onFlashLight(){

    }
    public void offFlashLight(){

    }

}
