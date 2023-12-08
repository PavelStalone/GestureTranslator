package com.ortin.gesturetranslator.main.viewmodel

import com.ortin.gesturetranslator.common.presentation.Reducer
import com.ortin.gesturetranslator.common.presentation.ViewModel
import com.ortin.gesturetranslator.domain.managers.SettingsManager
import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.usecases.ChangesThemeUseCase
import com.ortin.gesturetranslator.main.SettingsScreenIntent
import com.ortin.gesturetranslator.main.SettingsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val changesThemeUseCase: ChangesThemeUseCase,
    private val saveSettingsManager: SettingsManager
) : ViewModel<SettingsScreenState, SettingsScreenIntent>() {

    private val reducer = SettingsReducer(SettingsScreenState.initial())
    override val state: StateFlow<SettingsScreenState>
        get() = reducer.state

    private fun sendEvent(event: SettingsScreenIntent) {
        reducer.sendIntent(event)
    }

    private var currentSettings = SettingsDomain()

    fun onStart() {
        val settings = saveSettingsManager.getSettings()
        currentSettings = settings

        sendEvent(
            event = SettingsScreenIntent.ChangeTheme(
                isDarkMode = settings.theme
            )
        )
        sendEvent(
            event = SettingsScreenIntent.ShowPercent(
                isShowPercent = settings.percent
            )
        )
        sendEvent(
            event = SettingsScreenIntent.ChangeSpeedFrameDetection(
                speedFrameDetection = settings.speedFrameDetection
            )
        )
        sendEvent(
            event = SettingsScreenIntent.ChangeProcessingTools(
                isGpu = settings.gpu
            )
        )
    }

    fun onStop() {
        saveSettingsManager.saveSettings(currentSettings)
    }

    fun changeTheme() {
        sendEvent(
            event = SettingsScreenIntent.ChangeTheme(
                isDarkMode = !state.value.isDarkMode
            )
        )
        currentSettings = currentSettings.copy(
            theme = state.value.isDarkMode
        )

        changesThemeUseCase.execute(state.value.isDarkMode)
    }

    fun changeProcessingTool() {
        sendEvent(
            event = SettingsScreenIntent.ChangeProcessingTools(
                isGpu = !state.value.isGpu
            )
        )
        currentSettings = currentSettings.copy(
            gpu = state.value.isGpu
        )
    }

    fun changeShowPercent() {
        sendEvent(
            event = SettingsScreenIntent.ShowPercent(
                isShowPercent = !state.value.isShowPercent
            )
        )
        currentSettings = currentSettings.copy(
            percent = state.value.isShowPercent
        )
    }

    fun changeSpeedFrameDetection(newSpeedFrameDetection: Int) {
        sendEvent(
            event = SettingsScreenIntent.ChangeSpeedFrameDetection(
                speedFrameDetection = newSpeedFrameDetection
            )
        )
        currentSettings = currentSettings.copy(
            speedFrameDetection = newSpeedFrameDetection
        )
    }

    private class SettingsReducer(initial: SettingsScreenState) :
        Reducer<SettingsScreenState, SettingsScreenIntent>(initial) {
        override fun reduce(oldState: SettingsScreenState, intent: SettingsScreenIntent) {
            when (intent) {
                is SettingsScreenIntent.ChangeTheme -> {
                    setState(
                        oldState.copy(isDarkMode = intent.isDarkMode)
                    )
                }

                is SettingsScreenIntent.ChangeProcessingTools -> {
                    setState(
                        oldState.copy(isGpu = intent.isGpu)
                    )
                }

                is SettingsScreenIntent.ShowPercent -> {
                    setState(
                        oldState.copy(isShowPercent = intent.isShowPercent)
                    )
                }

                is SettingsScreenIntent.ChangeSpeedFrameDetection -> {
                    setState(
                        oldState.copy(speedFrameDetection = intent.speedFrameDetection)
                    )
                }
            }
        }
    }
}
