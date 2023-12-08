package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.app.usecases.ChangesThemeUseCaseImpl
import com.ortin.gesturetranslator.domain.usecases.ChangesThemeUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModuleBind {
    @Binds
    @Singleton
    fun bindRecognizeCoordinateUseCase(impl: RecognizeCoordinateUseCaseImpl): RecognizeCoordinateUseCase

    @Binds
    @Singleton
    fun bindChangesThemeUseCase(impl: ChangesThemeUseCaseImpl): ChangesThemeUseCase
}
