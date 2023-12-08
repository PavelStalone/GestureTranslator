package com.ortin.gesturetranslator.main.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.ortin.gesturetranslator.common.presentation.Reducer
import com.ortin.gesturetranslator.common.presentation.ViewModel
import com.ortin.gesturetranslator.domain.managers.MediaPipeManagerDomain
import com.ortin.gesturetranslator.domain.managers.SettingsManager
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.main.GalleryScreenIntent
import com.ortin.gesturetranslator.main.GalleryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val mediaPipeManager: MediaPipeManagerDomain,
    private val recognizeCoordinateUseCase: RecognizeCoordinateUseCase,
    private val settingsManager: SettingsManager
) : ViewModel<GalleryScreenState, GalleryScreenIntent>() {

    private val reducer = GalleryReducer(GalleryScreenState.initial())
    override val state: StateFlow<GalleryScreenState>
        get() = reducer.state

    private fun sendEvent(event: GalleryScreenIntent) {
        reducer.sendIntent(event)
    }

    private suspend fun changeMediaPipeSettings() {
        val settings = settingsManager.getSettings()
        mediaPipeManager.setSettingsModel(
            SettingsMediaPipe(
                currentDelegate = if (settings.gpu) {
                    SettingsMediaPipe.Delegation.DELEGATE_GPU
                } else {
                    SettingsMediaPipe.Delegation.DELEGATE_CPU
                },
                runningMode = SettingsMediaPipe.InputMode.IMAGE
            )
        )
    }

    fun selectedImage(image: Bitmap) {
        viewModelScope.launch {
            sendEvent(
                GalleryScreenIntent.StartLoadDialog(
                    description = "Идет настройка переводчика"
                )
            )
            changeMediaPipeSettings()
            sendEvent(GalleryScreenIntent.StopLoadDialog)
        }.invokeOnCompletion {
            val imageDetected = mediaPipeManager.detectImage(image)
            sendEvent(GalleryScreenIntent.OnRecognizedLetterChange(letter = imageDetected?.let {
                recognizeCoordinateUseCase.invoke(it).label
            } ?: ""))
        }
    }

    fun someError() {
        sendEvent(
            GalleryScreenIntent.ShowWarningDialog(
                title = "Что-то пошло не так",
                description = "Не волнуйтесь, это просто ошибка"
            )
        )
    }

    fun closeWarning(){
        sendEvent(
            GalleryScreenIntent.CloseWarningDialog
        )
    }

    private class GalleryReducer(initial: GalleryScreenState) :
        Reducer<GalleryScreenState, GalleryScreenIntent>(initial) {
        override fun reduce(
            oldState: GalleryScreenState, intent: GalleryScreenIntent
        ) {
            when (intent) {
                is GalleryScreenIntent.OnRecognizedLetterChange -> {
                    setState(
                        oldState.copy(recognizedLetter = intent.letter)
                    )
                }

                is GalleryScreenIntent.StartLoadDialog -> {
                    setState(
                        oldState.copy(
                            showDialogLoader = true,
                            descriptionLoaderDialog = intent.description
                        )
                    )
                }

                GalleryScreenIntent.StopLoadDialog -> {
                    setState(
                        oldState.copy(showDialogLoader = false)
                    )
                }

                GalleryScreenIntent.CloseWarningDialog -> {
                    setState(
                        oldState.copy(
                            showWarningDialog = false
                        )
                    )
                }

                is GalleryScreenIntent.ShowWarningDialog -> {
                    setState(
                        oldState.copy(
                            showWarningDialog = true,
                            warningTitle = intent.title,
                            warningDescription = intent.description
                        )
                    )
                }
            }
        }
    }
}
