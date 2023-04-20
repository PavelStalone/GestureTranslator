package com.example.gesturetranslator.di;

import android.content.Context;

import com.example.gesturetranslator.core.repository.RecognizeImageRepositoryImpl;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.TFLManager;
import com.example.gesturetranslator.core.managers.tensor_flow_lite_manager.TFLManagerImpl;
import com.example.gesturetranslator.domain.repository.RecognizeImageRepository;

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
    RecognizeImageRepository provideRecogniseImageRepository(TFLManager tflManager) {
        return new RecognizeImageRepositoryImpl(tflManager);
    }

    @Provides
    @Singleton
    TFLManager provideTFLManager(@ApplicationContext Context context) {
        return new TFLManagerImpl(context);
    }
}
