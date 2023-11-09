package com.ortin.gesturetranslator.data.di

import android.app.Application
import com.ortin.gesturetranslator.data.db.UserDao
import com.ortin.gesturetranslator.data.db.UserRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserDaoModule {
    @Provides
    @Singleton
    fun provideUserDao(application: Application): UserDao {
        return UserRoomDB.createDataBase(application).userDao
    }
}
