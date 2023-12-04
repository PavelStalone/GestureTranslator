package com.ortin.gesturetranslator.network.models

sealed class NetworkResponse<out T> {
    data class Success<T>(val body: T) : NetworkResponse<T>()

    data class Error(val throwable: Throwable?) : NetworkResponse<Nothing>()
}
