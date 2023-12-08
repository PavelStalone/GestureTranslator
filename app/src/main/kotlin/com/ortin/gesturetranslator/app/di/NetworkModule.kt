package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.domain.repository.AutocorrectTextRepository
import com.ortin.gesturetranslator.network.repository.AutocorrectTextRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    @Singleton
    fun bindAutocorrectTextRepository(impl: AutocorrectTextRepositoryImpl): AutocorrectTextRepository
}
