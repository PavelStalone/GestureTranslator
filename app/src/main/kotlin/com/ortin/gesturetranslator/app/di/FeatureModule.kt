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
interface FeatureModuleBind {
    @Binds
    @Singleton
    fun bindCameraManager(impl: CameraManagerImpl): CameraManager

    @Binds
    @Singleton
    fun bindLoadImageRepository(impl: LoadImageRepositoryImpl): LoadImageRepository

    @Binds
    @Singleton
    fun bindWordCompilerManager(impl: WordCompilerManagerImpl): WordCompilerManager

    @Binds
    @Singleton
    fun bindWordCompilerRepository(impl: WordCompilerRepositoryImpl): WordCompilerRepository
}
