package com.ortin.gesturetranslator.app.usecases

import androidx.appcompat.app.AppCompatDelegate
import com.ortin.gesturetranslator.domain.usecases.ChangesThemeUseCase
import javax.inject.Inject

class ChangesThemeUseCaseImpl @Inject constructor(): ChangesThemeUseCase {
    override fun execute(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
    }
}
