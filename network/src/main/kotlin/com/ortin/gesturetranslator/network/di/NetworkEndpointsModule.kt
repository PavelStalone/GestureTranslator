package com.ortin.gesturetranslator.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EndpointUrl(val qualifier: String)

@Module
@InstallIn(SingletonComponent::class)
object NetworkEndpointsModule {

    @Singleton
    @Named("HuggingFace")
    @Provides
    fun provideHuggingFaceHost(): String = "https://api-inference.huggingface.co/models/AccessAndrei/tired"

}
