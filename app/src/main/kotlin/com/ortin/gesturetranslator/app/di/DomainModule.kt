package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeImageUseCase
import com.ortin.gesturetranslator.domain.usecases.SaveLoadSettingsUseCase
import com.ortin.gesturetranslator.domain.usecases.WordCompileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    @Singleton
    fun provideLoadImageUseCase(loadImageRepository: LoadImageRepository): LoadImageUseCase {
        return LoadImageUseCase(loadImageRepository)
    }

    @Provides
    @Singleton
    fun provideWordCompileUseCase(wordCompilerRepository: WordCompilerRepository): WordCompileUseCase {
        return WordCompileUseCase(wordCompilerRepository)
    }

    @Provides
    @Singleton
    fun provideDetectHandUseCase(handDetectionRepository: HandDetectionRepository): DetectHandUseCase {
        return DetectHandUseCase(handDetectionRepository)
    }

    @Provides
    @Singleton
    fun provideRecognizeCoordinateUseCase(recognizeCoordinateRepository: RecognizeCoordinateRepository): RecognizeCoordinateUseCase {
        return RecognizeCoordinateUseCase(recognizeCoordinateRepository)
    }

    @Provides
    @Singleton
    fun provideSaveSettingsUseCase(settingsRepository: SettingsRepository): SaveLoadSettingsUseCase {
        return SaveLoadSettingsUseCase(settingsRepository)
    }
}
