package com.ortin.gesturetranslator.data.validation

import android.content.res.Resources
import com.ortin.gesturetranslator.data.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPasswordValidator @Inject constructor() {
    fun execute(password: String): ValidationResult {
        if (password.isEmpty()) {
            return ValidationResult(
                isSuccess = false,
                error = Resources.getSystem().getString(R.string.user_password_error)
            )
        }
        return ValidationResult(
            isSuccess = true
        )
    }
}
