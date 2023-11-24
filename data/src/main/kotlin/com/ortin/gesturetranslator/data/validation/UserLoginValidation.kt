package com.ortin.gesturetranslator.data.validation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLoginValidation @Inject constructor() {
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
