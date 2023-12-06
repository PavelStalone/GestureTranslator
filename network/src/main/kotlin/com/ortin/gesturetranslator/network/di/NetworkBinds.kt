package com.ortin.gesturetranslator.network.di

import com.ortin.gesturetranslator.network.AutoCorrectDataSource
import com.ortin.gesturetranslator.network.AutoCorrectDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkBinds {

    @Binds
    fun bindAutoCorrectDataSource(impl: AutoCorrectDataSourceImpl): AutoCorrectDataSource
}
