package com.ortin.gesturetranslator.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

enum class DoDispatchers {
    IO,
    DEFAULT
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: DoDispatchers)

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    @Dispatcher(DoDispatchers.DEFAULT)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    @Dispatcher(DoDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
