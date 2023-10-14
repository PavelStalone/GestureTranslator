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
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class, ActivityComponent::class)
class DomainModule {
    @Provides
    fun provideLoadImageUseCase(loadImageRepository: LoadImageRepository?): LoadImageUseCase {
        return LoadImageUseCase(loadImageRepository)
    }

    @Provides
    fun provideWordCompileUseCase(wordCompilerRepository: WordCompilerRepository?): WordCompileUseCase {
        return WordCompileUseCase(wordCompilerRepository)
    }

    @Provides
    fun provideRecognizeImageUseCase(recognizeImageRepository: RecognizeImageRepository?): RecognizeImageUseCase {
        return RecognizeImageUseCase(recognizeImageRepository)
    }

    @Provides
    fun provideDetectHandUseCase(handDetectionRepository: HandDetectionRepository?): DetectHandUseCase {
        return DetectHandUseCase(handDetectionRepository)
    }

    @Provides
    fun provideRecognizeCoordinateUseCase(recognizeCoordinateRepository: RecognizeCoordinateRepository?): RecognizeCoordinateUseCase {
        return RecognizeCoordinateUseCase(recognizeCoordinateRepository)
    }

    @Provides
    fun provideSaveSettingsUseCase(settingsRepository: SettingsRepository?): SaveLoadSettingsUseCase {
        return SaveLoadSettingsUseCase(settingsRepository)
    }
}
