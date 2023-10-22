package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.domain.repository.LoadImageRepository
import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository
import com.ortin.gesturetranslator.feature.managers.camera.CameraManager
import com.ortin.gesturetranslator.feature.managers.camera.CameraManagerImpl
import com.ortin.gesturetranslator.feature.managers.word_compiler.WordCompilerManager
import com.ortin.gesturetranslator.feature.managers.word_compiler.WordCompilerManagerImpl
import com.ortin.gesturetranslator.feature.repository.LoadImageRepositoryImpl
import com.ortin.gesturetranslator.feature.repository.WordCompilerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureModuleBind {
    @Binds
    @Singleton
    abstract fun bindCameraManager(cameraManagerImpl: CameraManagerImpl): CameraManager

    @Binds
    @Singleton
    abstract fun provideLoadImageRepository(loadImageRepositoryImpl: LoadImageRepositoryImpl): LoadImageRepository

    @Binds
    @Singleton
    abstract fun provideWordCompilerManager(wordCompilerManagerImpl: WordCompilerManagerImpl): WordCompilerManager

    @Binds
    @Singleton
    abstract fun provideWordCompilerRepository(wordCompilerRepositoryImpl: WordCompilerRepositoryImpl): WordCompilerRepository
}
