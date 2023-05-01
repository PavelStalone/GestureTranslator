package com.example.gesturetranslator.di;

import com.example.gesturetranslator.domain.repository.HandDetectionRepository;
import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.domain.repository.RecognizeImageRepository;
import com.example.gesturetranslator.domain.usecases.DetectHandUseCase;
import com.example.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.example.gesturetranslator.domain.usecases.RecognizeImageUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;

@Module
@InstallIn(FragmentComponent.class)
public class DomainModule {

    @Provides
    LoadImageUseCase provideLoadImageUseCase(LoadImageRepository loadImageRepository) {
        return new LoadImageUseCase(loadImageRepository);
    }

    @Provides
    RecognizeImageUseCase provideRecognizeImageUseCase(RecognizeImageRepository recognizeImageRepository) {
        return new RecognizeImageUseCase(recognizeImageRepository);
    }

    @Provides
    DetectHandUseCase provideDetectHandUseCase(HandDetectionRepository handDetectionRepository) {
        return new DetectHandUseCase(handDetectionRepository);
    }
}
