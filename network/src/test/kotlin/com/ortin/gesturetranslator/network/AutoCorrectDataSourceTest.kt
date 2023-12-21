package com.ortin.gesturetranslator.network

import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
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
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.time.Duration.Companion.seconds

@RunWith(JUnit4::class)
class AutoCorrectDataSourceTest {

    private lateinit var dataSource: AutoCorrectDataSource

    @Before
    fun setUp() {
        // Given: setting up test dependencies
        val json = Json { ignoreUnknownKeys = true }

        dataSource = AutoCorrectDataSourceImpl(
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
            huggingFaceHost = "192.168.1.73:8080",
            dispatcher = Dispatchers.IO
        )
    }

    @Test
    fun correctText() = runTest {
        // When: calling the correctText method with incorrect text as input
        val response = dataSource.correctText(
            model = RecognizedTextModel(data = "Кагда ты возвращашься назад?")
        )

        // Then: print the result and manually check it
        // Note: It's not possible to use assertEquals here, because the neural network's response may vary from the expected result
        println("Response: $response")
    }
}
