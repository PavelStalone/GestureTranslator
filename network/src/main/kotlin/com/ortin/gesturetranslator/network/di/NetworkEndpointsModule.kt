package com.ortin.gesturetranslator.network.di

import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Named("HuggingFace")
@Provides
fun provideHuggingFaceHost(): String {
    return "https://api-inference.huggingface.co/models/AccessAndrei/tired"
}
