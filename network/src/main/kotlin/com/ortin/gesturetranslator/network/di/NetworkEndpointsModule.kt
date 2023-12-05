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
    @Named("HuggingFace")
    fun provideHuggingFaceHost(): String = "api-inference.huggingface.co"

    @Provides
    @Singleton
    fun provideInferenceToken(): String = BuildConfig.INFERENCE_TOKEN
}
