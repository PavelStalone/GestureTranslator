package com.example.gesturetranslator.di;

import com.example.gesturetranslator.domain.repository.LoadImageRepository;
import com.example.gesturetranslator.domain.usecases.LoadImageUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class DomainModule {

    @Provides
    LoadImageUseCase provideLoadImageUseCase(LoadImageRepository loadImageRepository){
        return new LoadImageUseCase(loadImageRepository);
    }
}
