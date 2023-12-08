package com.ortin.gesturetranslator.domain.models

/**
 * The class responsible for storing and transmitting responses from the API
 */
sealed class NetworkResponse<out T> {

    /**
     * Data class, which represents a successful response from the API with a body field of type T
     */
    data class Success<T>(val body: T) : NetworkResponse<T>()

    /**
     * Data class, which represents an error with an error field of type Throwable to store any type of error information
     */
    data class Error(val throwable: Throwable?) : NetworkResponse<Nothing>()
}
