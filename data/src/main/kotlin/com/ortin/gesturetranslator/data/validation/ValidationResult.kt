package com.ortin.gesturetranslator.data.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
