package com.ortin.gesturetranslator.data.validation

class ValidateUserLogin {
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
