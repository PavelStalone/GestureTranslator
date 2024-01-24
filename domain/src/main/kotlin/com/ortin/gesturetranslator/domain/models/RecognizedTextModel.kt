package com.ortin.gesturetranslator.domain.models

import kotlinx.serialization.Serializable

/**
 * It's a model, which will put into POST request for sending recognized text to nlp model
 *
 * @property inputs collected text, ready for sending
 */
@Serializable
data class RecognizedTextModel(
    val data: String
)
