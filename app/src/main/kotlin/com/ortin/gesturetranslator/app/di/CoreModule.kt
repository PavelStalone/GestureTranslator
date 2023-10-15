package com.ortin.gesturetranslator.app.di

import android.content.Context
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManagerImpl
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager
import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManagerImpl
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.TfLiteManager
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.TfLiteManagerImpl
import com.ortin.gesturetranslator.core.repository.HandDetectionRepositoryImpl
import com.ortin.gesturetranslator.core.repository.RecognizeCoordinateRepositoryImpl
import com.ortin.gesturetranslator.core.repository.RecognizeImageRepositoryImpl
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {
    @Provides
    @Singleton
    fun provideRecogniseImageRepository(tfLiteManager: TfLiteManager): RecognizeImageRepository {
        return RecognizeImageRepositoryImpl(tfLiteManager)
    }

    @Provides
    @Singleton
    fun provideTFLManager(@ApplicationContext context: Context?): TfLiteManager {
        return TfLiteManagerImpl(context, "MobileNetV2.tflite")
    }

    @Provides
    @Singleton
    fun provideHandDetectionRepository(mediaPipeManager: MediaPipeManager): HandDetectionRepository {
        return HandDetectionRepositoryImpl(mediaPipeManager)
    }

    @Provides
    @Singleton
    fun provideMediaPipeManager(@ApplicationContext context: Context): MediaPipeManager {
        return MediaPipeManagerImpl(context, "hand_landmarker.task")
    }

    @Provides
    @Singleton
    fun provideRecognizeCoordinateRepository(modelCoordinateManager: ModelCoordinateManager): RecognizeCoordinateRepository {
        return RecognizeCoordinateRepositoryImpl(modelCoordinateManager)
    }

    @Provides
    @Singleton
    fun provideModelCoordinateManager(): ModelCoordinateManager {
        return ModelCoordinateManagerImpl()
    }
}
