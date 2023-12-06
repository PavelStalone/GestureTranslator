package com.ortin.gesturetranslator.network

import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.di.GtDispatchers
import com.ortin.gesturetranslator.network.di.EndpointUrl
import com.ortin.gesturetranslator.network.models.CorrectedTextModel
import com.ortin.gesturetranslator.network.models.NetworkResponse
import com.ortin.gesturetranslator.network.models.RecognizedTextModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Interface for autocorrecting recognized text bu model on HuggingFace
 */
interface AutoCorrectDataSource {

    /**
     * Method for sending request to switch user's password
     *
     * @param [model] data class, which contains user's current password and new password. Uses the [RecognizedTextModel] data class
     * @return network response of CorrectedTextModel
     */
    suspend fun correctText(model: RecognizedTextModel): NetworkResponse<List<CorrectedTextModel>>
}

class AutoCorrectDataSourceImpl(
    private val token: String,
    private val client: HttpClient,
    @EndpointUrl("HuggingFace") private val huggingFaceHost: String,
    @Dispatcher(GtDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : AutoCorrectDataSource {
    override suspend fun correctText(model: RecognizedTextModel) = withContext(dispatcher) {
        return@withContext try {
            client.post {
                url {
                    host = huggingFaceHost
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    path("models", "AccessAndrei", "tired")
                }
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(model)
            }.let { response ->
                Timber.d("Response %s", response)
                NetworkResponse.Success(response.body<List<CorrectedTextModel>>())
            }
        } catch (exception: Exception) {
            when (exception) {
                is RedirectResponseException -> Timber.e("RedirectResponseException: ${exception.response}")
                is ClientRequestException -> Timber.e("ClientRequestException: ${exception.response}")
                is ServerResponseException -> Timber.e("ServerResponseException: ${exception.response}")
                else -> Timber.e("Exception: $exception")
            }
            NetworkResponse.Error(throwable = exception)
        }
    }
}
