package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManagerImpl
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManagerImpl
import com.ortin.gesturetranslator.core.repository.HandDetectionRepositoryImpl
import com.ortin.gesturetranslator.core.repository.RecognizeCoordinateRepositoryImpl
import com.ortin.gesturetranslator.core.repository.RecognizeImageRepositoryImpl
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModuleBind {
    @Binds
    @Singleton
    abstract fun provideModelCoordinateManager(modelCoordinateManagerImpl: ModelCoordinateManagerImpl): ModelCoordinateManager

    @Binds
    @Singleton
    abstract fun provideRecognizeCoordinateRepository(recognizeCoordinateRepositoryImpl: RecognizeCoordinateRepositoryImpl): RecognizeCoordinateRepository

    @Binds
    @Singleton
    abstract fun provideMediaPipeManager(mediaPipeManagerImpl: MediaPipeManagerImpl): MediaPipeManager

    @Binds
    @Singleton
    abstract fun provideHandDetectionRepository(handDetectionRepositoryImpl: HandDetectionRepositoryImpl): HandDetectionRepository

    @Binds
    @Singleton
    abstract fun provideRecogniseImageRepository(recognizeImageRepositoryImpl: RecognizeImageRepositoryImpl): RecognizeImageRepository
}
