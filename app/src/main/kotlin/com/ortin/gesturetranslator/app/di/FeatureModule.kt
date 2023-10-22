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
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureModule {
    @Provides
    @Singleton
    fun provideLoadImageRepository(cameraManager: CameraManager): LoadImageRepository {
        return LoadImageRepositoryImpl(cameraManager)
    }

    @Provides
    @Singleton
    fun provideWordCompilerRepository(wordCompilerManager: WordCompilerManager): WordCompilerRepository {
        return WordCompilerRepositoryImpl(wordCompilerManager)
    }

    @Provides
    @Singleton
    fun provideWordCompilerManager(): WordCompilerManager {
        return WordCompilerManagerImpl()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureModuleBind {
    @Binds
    abstract fun bindCameraManager(cameraManagerImpl: CameraManagerImpl): CameraManager
}
