package com.ortin.gesturetranslator.network.repository

import com.ortin.gesturetranslator.domain.models.CorrectedTextModel
import com.ortin.gesturetranslator.domain.models.NetworkResponse
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.domain.repository.AutocorrectTextRepository
import com.ortin.gesturetranslator.network.AutoCorrectDataSourceImpl
import com.ortin.gesturetranslator.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

class AutocorrectTextRepositoryImpl: AutocorrectTextRepository {
    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun correctText(recognizedTextModel: RecognizedTextModel): NetworkResponse<CorrectedTextModel> {
        return AutoCorrectDataSourceImpl(
            client = HttpClient(OkHttp.create()) {
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
            },
            token = BuildConfig.INFERENCE_TOKEN,
            huggingFaceHost = "api-inference.huggingface.co",
            dispatcher = Dispatchers.IO
        ).correctText(recognizedTextModel)
    }
}