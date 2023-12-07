package com.ortin.gesturetranslator.network.di

import com.ortin.gesturetranslator.network.AutoCorrectDataSource
import com.ortin.gesturetranslator.network.AutoCorrectDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

/**
 * A Hilt module that provides network-related dependencies.
 * This module configures the HTTP client and JSON processing for network communication.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a configured instance of [Json] for JSON processing with Kotlinx serialization.
     */
    @Provides
    @Singleton
    @Named("network")
    fun provideJson() = Json { ignoreUnknownKeys = true }

    /**
     * Provides a singleton instance of [HttpClientEngine] using the OkHttp engine.
     */
    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create()

    /**
     * Provides a singleton instance of [HttpClient] with dependencies [json] and [engine].
     * Configures the HTTP client with the provided [HttpClientEngine], JSON processing, and request/response logging.
     */
    @Provides
    @Singleton
    fun provideHttpClient(@Named("network") json: Json, engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) { json(json) }

            install(HttpTimeout) {
                connectTimeoutMillis = 20.seconds.inWholeMilliseconds
                requestTimeoutMillis = 60.seconds.inWholeMilliseconds
                socketTimeoutMillis = 20.seconds.inWholeMilliseconds
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }
}
