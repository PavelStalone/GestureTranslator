package com.ortin.gesturetranslator.network.repository

import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.di.GtDispatchers
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Singleton
class AutocorrectTextRepositoryImpl @Inject constructor (
    @Dispatcher(GtDispatchers.IO) private val dispatcher: CoroutineDispatcher
): AutocorrectTextRepository {
    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun correctText(recognizedTextModel: RecognizedTextModel) = withContext(dispatcher) {
        return@withContext AutoCorrectDataSourceImpl(
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
