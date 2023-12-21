package com.ortin.gesturetranslator.network

import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.di.GtDispatchers
import com.ortin.gesturetranslator.domain.models.CorrectedTextModel
import com.ortin.gesturetranslator.domain.models.NetworkResponse
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.network.di.EndpointUrl
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
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Interface for autocorrecting recognized text by model on HuggingFace
 */
interface AutoCorrectDataSource {

    /**
     * Method for sending request to switch user's password
     *
     * @param [model] data class, which contains user's current password and new password. Uses the [RecognizedTextModel] data class
     * @return network response of CorrectedTextModel
     */
    suspend fun correctText(model: RecognizedTextModel): NetworkResponse<CorrectedTextModel>
}

class AutoCorrectDataSourceImpl @Inject constructor(
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
                    protocol = URLProtocol.HTTP
                    contentType(ContentType.Application.Json)
                    path("predict")
                }
                setBody(model)
            }.let { response ->
                Timber.d("Response %s", response)
                NetworkResponse.Success(response.body<CorrectedTextModel>())
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
