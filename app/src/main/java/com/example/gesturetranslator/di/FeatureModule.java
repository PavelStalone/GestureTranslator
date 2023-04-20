package com.example.gesturetranslator.di;

import android.content.Context;

import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.feature.managers.camera_manager.CameraManager;
import com.example.gesturetranslator.feature.managers.camera_manager.CameraManagerImpl;
import com.example.gesturetranslator.feature.repository.LoadImageRepositoryImpl;

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
