package com.ortin.gesturetranslator.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * It's a model, which reflects the json response scheme from api
 *
 * @property correctedText collected text, received as a response from the server
 */
@Serializable
data class CorrectedTextModel(
    @SerialName("generated_text") val correctedText: String
)
