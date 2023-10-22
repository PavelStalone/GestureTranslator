package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.data.repository.SettingsRepositoryImpl
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.data.storage.SharedPrefSettingsStorage
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleBind {
    @Binds
    @Singleton
    abstract fun provideSettingsStorage(sharedPrefSettingsStorage: SharedPrefSettingsStorage): SettingsStorage

    @Binds
    @Singleton
    abstract fun provideSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}
