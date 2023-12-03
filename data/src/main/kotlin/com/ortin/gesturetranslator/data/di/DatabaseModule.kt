package com.ortin.gesturetranslator.data.di

import android.content.Context
import com.ortin.gesturetranslator.data.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) = UserDatabase.buildDataBase(context)
}
