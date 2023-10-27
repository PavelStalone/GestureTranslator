package com.ortin.gesturetranslator.data.validation

class ValidateUserPassword {
    fun execute(password: String): ValidationResult {
        if (password.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите пароль"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
