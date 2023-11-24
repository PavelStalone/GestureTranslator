package com.ortin.gesturetranslator.data.validation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPasswordValidation @Inject constructor() {
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
