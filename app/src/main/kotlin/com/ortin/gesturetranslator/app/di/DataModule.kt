package com.ortin.gesturetranslator.app.di

import com.ortin.gesturetranslator.data.repository.SettingsRepositoryImpl
import com.ortin.gesturetranslator.data.repository.UserRepositoryImpl
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.data.storage.SharedPrefSettingsStorage
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import com.ortin.gesturetranslator.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindSettingsStorage(sharedPrefSettingsStorage: SharedPrefSettingsStorage): SettingsStorage

    @Binds
    @Singleton
    fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    fun bindUserRoomDatabaseRepository(impl: UserRepositoryImpl): UserRepository
}
