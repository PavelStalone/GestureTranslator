package com.example.gesturetranslator.di;

import com.example.domain.repository.LoadImageRepository;
import com.example.domain.usecases.LoadImageUseCase;

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
