package com.ortin.gesturetranslator.data.validation

import android.content.res.Resources
import com.ortin.gesturetranslator.data.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLoginValidator @Inject constructor() {
    fun execute(login: String): ValidationResult {
        if (login.isEmpty()) {
            return ValidationResult(
                isSuccess = false,
                error = Resources.getSystem().getString(R.string.user_login_error)
            )
        }

        return ValidationResult(
            isSuccess = true
        )
    }
}
