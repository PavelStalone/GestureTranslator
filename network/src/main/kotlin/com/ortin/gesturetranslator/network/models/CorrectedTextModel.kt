package com.ortin.gesturetranslator.network.models

import kotlinx.serialization.Serializable

/**
 * It's a model, which reflects the json response scheme from api
 *
 * @property correctedText collected text, received as a response from the server
 */

@Serializable
data class CorrectedTextModel(val correctedText: String)
