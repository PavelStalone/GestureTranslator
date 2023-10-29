package com.ortin.gesturetranslator.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ModelPath

@Module
@InstallIn(SingletonComponent::class)
object MediaPipeModule {
    @Provides
    @Singleton
    @ModelPath
    fun provideModelPath(): String = "hand_landmarker.task"
}
