package com.ortin.gesturetranslator.data.validation

import javax.inject.Inject

class ValidateUserLogin @Inject constructor() {
    fun execute(login: String): ValidationResult {
        if (login.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите логин"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}
