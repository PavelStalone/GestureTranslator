package com.ortin.gesturetranslator.network.di

import com.ortin.gesturetranslator.network.BuildConfig
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

    @Provides
    @Singleton
    @EndpointUrl("HuggingFace")
    fun provideHuggingFaceHost(): String = "192.168.1.73:8080"

    @Provides
    @Singleton
    fun provideInferenceToken(): String = BuildConfig.INFERENCE_TOKEN
}
