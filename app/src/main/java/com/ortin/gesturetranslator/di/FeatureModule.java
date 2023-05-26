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

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class FeatureModule {
    @Provides
    LoadImageRepository provideLoadImageRepository(CameraManager cameraManager) {
        return new LoadImageRepositoryImpl(cameraManager);
    }

    @Provides
    CameraManager provideCameraManager(@ActivityContext Context context) {
        return new CameraManagerImpl(context);
    }

    @Provides
    WordCompilerRepository provideWordCompilerRepository(WordCompilerManager wordCompilerManager) {
        return new WordCompilerRepositoryImpl(wordCompilerManager);
    }

    @Provides
    WordCompilerManager provideWordCompilerManager() {
        return new WordCompilerManagerImpl();
    }
}
