package com.ortin.gesturetranslator.data.di

import com.ortin.gesturetranslator.data.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun provideUserDao(database: UserDatabase) = database.userDao()
}
