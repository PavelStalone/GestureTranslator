package com.ortin.gesturetranslator.app.di

import android.content.Context
import com.ortin.gesturetranslator.data.repository.SettingsRepositoryImpl
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.data.storage.SharedPrefSettingsStorage
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideSettingsRepository(settingsStorage: SettingsStorage?): SettingsRepository {
        return SettingsRepositoryImpl(settingsStorage)
    }

    @Provides
    @Singleton
    fun provideSettingsStorage(@ApplicationContext context: Context?): SettingsStorage {
        return SharedPrefSettingsStorage(context)
    }
}
