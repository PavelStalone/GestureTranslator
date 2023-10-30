package com.ortin.gesturetranslator.data.di

import android.app.Application
import com.ortin.gesturetranslator.data.db.UserDao
import com.ortin.gesturetranslator.data.db.UserRoomDB
import com.ortin.gesturetranslator.data.repository.SettingsRepositoryImpl
import com.ortin.gesturetranslator.data.repository.UserRoomDatabaseRepositoryImpl
import com.ortin.gesturetranslator.data.storage.SettingsStorage
import com.ortin.gesturetranslator.data.storage.SharedPrefSettingsStorage
import com.ortin.gesturetranslator.domain.repository.SettingsRepository
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
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
    fun bindUserRoomDatabaseRepository(impl: UserRoomDatabaseRepositoryImpl): UserRoomDatabaseRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDao(application: Application): UserDao {
            return UserRoomDB.createDataBase(application).userDao
        }
    }
}
