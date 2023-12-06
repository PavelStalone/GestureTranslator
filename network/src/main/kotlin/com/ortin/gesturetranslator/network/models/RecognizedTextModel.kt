package com.ortin.gesturetranslator.network.models

import kotlinx.serialization.Serializable

/**
 * It's a model, which will put into Post request for sending recognized text to nlp model
 *
 * @property recognizedText collected text, ready for sending
 */
@Serializable
data class RecognizedTextModel( val recognizedText: String )
