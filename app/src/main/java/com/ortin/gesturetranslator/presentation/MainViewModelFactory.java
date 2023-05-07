package com.ortin.gesturetranslator.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeImageUseCase;

import javax.inject.Inject;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    @Inject
    LoadImageUseCase loadImageUseCase;

    @Inject
    RecognizeImageUseCase recognizeImageUseCase;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(loadImageUseCase, recognizeImageUseCase);
    }
}
