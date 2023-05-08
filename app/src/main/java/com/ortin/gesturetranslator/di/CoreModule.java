package com.ortin.gesturetranslator.di;

import android.content.Context;

import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager;
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManagerImpl;
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager;
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManagerManagerImpl;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.TfLiteManager;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.TfLiteManagerImpl;
import com.ortin.gesturetranslator.core.repository.HandDetectionRepositoryImpl;
import com.ortin.gesturetranslator.core.repository.RecognizeCoordinateRepositoryImpl;
import com.ortin.gesturetranslator.core.repository.RecognizeImageRepositoryImpl;
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository;
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository;
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class CoreModule {
    @Provides
    @Singleton
    RecognizeImageRepository provideRecogniseImageRepository(TfLiteManager tfLiteManager) {
        return new RecognizeImageRepositoryImpl(tfLiteManager);
    }

    @Provides
    @Singleton
    TfLiteManager provideTFLManager(@ApplicationContext Context context) {
        return new TfLiteManagerImpl(context, "MobileNetV2.tflite");
    }

    @Provides
    @Singleton
    HandDetectionRepository provideHandDetectionRepository(MediaPipeManager mediaPipeManager) {
        return new HandDetectionRepositoryImpl(mediaPipeManager);
    }

    @Provides
    @Singleton
    MediaPipeManager provideMediaPipeManager(@ApplicationContext Context context) {
        return new MediaPipeManagerImpl(context, "hand_landmarker.task");
    }

    @Provides
    @Singleton
    RecognizeCoordinateRepository provideRecognizeCoordinateRepository(ModelCoordinateManager modelCoordinateManager) {
        return new RecognizeCoordinateRepositoryImpl(modelCoordinateManager);
    }

    @Provides
    @Singleton
    ModelCoordinateManager provideModelCoordinateManager() {
        return new ModelCoordinateManagerManagerImpl();
    }
}
