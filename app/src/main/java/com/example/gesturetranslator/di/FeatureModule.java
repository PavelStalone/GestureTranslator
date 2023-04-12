package com.example.gesturetranslator.di;

import android.content.Context;

import com.example.domain.repository.LoadImageRepository;
import com.example.feature.camera_manager.CameraManager;
import com.example.feature.camera_manager.CameraManagerImpl;
import com.example.feature.repository.LoadImageRepositoryImpl;

import javax.inject.Singleton;

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
}
