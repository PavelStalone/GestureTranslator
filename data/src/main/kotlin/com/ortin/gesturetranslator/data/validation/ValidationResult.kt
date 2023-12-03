package com.ortin.gesturetranslator.data.validation

data class ValidationResult(
    val isSuccess: Boolean,
    val error: String? = null
)
