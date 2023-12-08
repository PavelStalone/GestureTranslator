package com.ortin.gesturetranslator.main

import androidx.compose.runtime.Immutable
import com.ortin.gesturetranslator.common.presentation.ModelIntent
import com.ortin.gesturetranslator.common.presentation.UiState

@Immutable
sealed class SettingsScreenIntent : ModelIntent {

    data class ChangeTheme(val isDarkMode: Boolean): SettingsScreenIntent()
    data class ChangeProcessingTools(val isGpu: Boolean): SettingsScreenIntent()
    data class ShowPercent(val isShowPercent: Boolean): SettingsScreenIntent()
    data class ChangeSpeedFrameDetection(val speedFrameDetection: Int): SettingsScreenIntent()
}

@Immutable
data class SettingsScreenState(
    val isDarkMode: Boolean,
    val isGpu: Boolean,
    val isShowPercent: Boolean,
    val speedFrameDetection: Int
) : UiState {
    companion object {
        fun initial() = SettingsScreenState(
            isDarkMode = false,
            isGpu = false,
            isShowPercent = false,
            speedFrameDetection = 10
        )
    }
}
