package com.ortin.gesturetranslator.feature.di

import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
@InstallIn(SingletonComponent::class)
object CameraModule {
    @Provides
    fun provideMainExecutor(@ApplicationContext context: Context): Executor {
        return ContextCompat.getMainExecutor(context)
    }

    @Provides
    fun provideExecutorService(): ExecutorService {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    fun provideCameraFuture(@ApplicationContext context: Context): ListenableFuture<ProcessCameraProvider> {
        return ProcessCameraProvider.getInstance(context)
    }
}
