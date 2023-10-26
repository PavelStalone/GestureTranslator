package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManagerImpl
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManagerImpl
import com.ortin.gesturetranslator.core.repository.HandDetectionRepositoryImpl
import com.ortin.gesturetranslator.core.repository.RecognizeCoordinateRepositoryImpl
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModuleBind {
    @Binds
    @Singleton
    fun bindModelCoordinateManager(impl: ModelCoordinateManagerImpl): ModelCoordinateManager

    @Binds
    @Singleton
    fun bindRecognizeCoordinateRepository(impl: RecognizeCoordinateRepositoryImpl): RecognizeCoordinateRepository

    @Binds
    @Singleton
    fun bindMediaPipeManager(impl: MediaPipeManagerImpl): MediaPipeManager

    @Binds
    @Singleton
    fun bindHandDetectionRepository(impl: HandDetectionRepositoryImpl): HandDetectionRepository
}
