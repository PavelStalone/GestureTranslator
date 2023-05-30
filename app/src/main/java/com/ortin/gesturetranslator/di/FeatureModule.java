package com.ortin.gesturetranslator.di;

import android.content.Context;

import com.ortin.gesturetranslator.domain.repository.LoadImageRepository;
import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository;
import com.ortin.gesturetranslator.feature.managers.camera.CameraManager;
import com.ortin.gesturetranslator.feature.managers.camera.CameraManagerImpl;
import com.ortin.gesturetranslator.feature.managers.word_compiler.WordCompilerManager;
import com.ortin.gesturetranslator.feature.managers.word_compiler.WordCompilerManagerImpl;
import com.ortin.gesturetranslator.feature.repository.LoadImageRepositoryImpl;
import com.ortin.gesturetranslator.feature.repository.WordCompilerRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FeatureModule {
    @Provides
    @Singleton
    LoadImageRepository provideLoadImageRepository(CameraManager cameraManager) {
        return new LoadImageRepositoryImpl(cameraManager);
    }

    @Provides
    @Singleton
    CameraManager provideCameraManager(@ApplicationContext Context context) {
        return new CameraManagerImpl(context);
    }

    @Provides
    @Singleton
    WordCompilerRepository provideWordCompilerRepository(WordCompilerManager wordCompilerManager) {
        return new WordCompilerRepositoryImpl(wordCompilerManager);
    }

    @Provides
    @Singleton
    WordCompilerManager provideWordCompilerManager() {
        return new WordCompilerManagerImpl();
    }
}
