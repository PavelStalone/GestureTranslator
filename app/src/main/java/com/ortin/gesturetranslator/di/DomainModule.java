package com.ortin.gesturetranslator.di;

import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository;
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository;
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository;
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository;
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase;
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeImageUseCase;

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

    @Provides
    RecognizeCoordinateUseCase provideRecognizeCoordinateUseCase(RecognizeCoordinateRepository recognizeCoordinateRepository) {
        return new RecognizeCoordinateUseCase(recognizeCoordinateRepository);
    }
}
