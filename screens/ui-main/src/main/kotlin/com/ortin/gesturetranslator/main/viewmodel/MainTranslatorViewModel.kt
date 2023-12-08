package com.ortin.gesturetranslator.main.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ortin.gesturetranslator.common.presentation.Reducer
import com.ortin.gesturetranslator.common.presentation.ViewModel
import com.ortin.gesturetranslator.domain.managers.CameraInputManager
import com.ortin.gesturetranslator.domain.managers.MediaPipeManagerDomain
import com.ortin.gesturetranslator.domain.managers.SettingsManager
import com.ortin.gesturetranslator.domain.managers.WorldCompileManager
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.NetworkResponse
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.usecases.CorrectTextUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.main.GalleryScreenIntent
import com.ortin.gesturetranslator.main.MainTranslatorScreenIntent
import com.ortin.gesturetranslator.main.MainTranslatorScreenState
import com.ortin.gesturetranslator.main.components.PaintHand.drawHand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainTranslatorViewModel @Inject constructor(
    private val cameraManager: CameraInputManager,
    private val worldCompileManager: WorldCompileManager,
    private val mediaPipeManager: MediaPipeManagerDomain,
    private val recognizeCoordinateUseCase: RecognizeCoordinateUseCase,
    private val settingsManager: SettingsManager,
    private val correctTextUseCase: CorrectTextUseCase
) : ViewModel<MainTranslatorScreenState, MainTranslatorScreenIntent>() {

    private val reducer = MainTranslatorReducer(MainTranslatorScreenState.initial())
    override val state: StateFlow<MainTranslatorScreenState>
        get() = reducer.state

    private var lifecycleOwner: LifecycleOwner? = null
    private var coroutineContext: CoroutineContext? = null

    private fun sendEvent(event: MainTranslatorScreenIntent) {
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
                runningMode = SettingsMediaPipe.InputMode.LIVE_STREAM
            )
        )
    }

    private fun startTranslating() {
        lifecycleOwner?.let { lifecycleOwner ->
            coroutineContext = viewModelScope.launch {
                worldCompileManager.clearState() // Стираем собранный текст
                combine(
                    mediaPipeManager.flow,
                    cameraManager.startListening(lifecycleOwner)
                        .onEach { mediaPipeManager.detectLiveStream(it.bitmap) },
                    ::mergeSources
                ).collect(::sendEvent)
            }
        } ?: {
            Timber.e("LifeCycleOwner is null")
            // Todo use Warning dialog
        }
    }

    private fun stopTranslating() {
        coroutineContext?.cancel()
    }

    fun onTranslatingStatusChanged(status: Boolean) {
        if (status) {
            viewModelScope.launch {
                sendEvent(
                    MainTranslatorScreenIntent.StartLoaderDialog(
                        description = "Идет настройка переводчика"
                    )
                )
                changeMediaPipeSettings()
                sendEvent(MainTranslatorScreenIntent.StopLoaderDialog)
            }.invokeOnCompletion {
                startTranslating()
            }
        } else {
            stopTranslating()
        }
    }

    fun onTextCorrectedStatusChanged(status: Boolean) {
        viewModelScope.launch {
            sendEvent(
                MainTranslatorScreenIntent.StartLoaderDialog(
                    description = "Идет загрузка"
                )
            )
            val response = correctTextUseCase(RecognizedTextModel(worldCompileManager.getWord()))
            when(response) {
                is NetworkResponse.Success -> {
                    sendEvent(
                        MainTranslatorScreenIntent.OnTextTranslatingChange(
                            response.body.correctedText
                        )
                    )
                }
                is NetworkResponse.Error -> { someError() }
            }
            sendEvent(
                MainTranslatorScreenIntent.StopLoaderDialog
            )
        }
    }

    fun bindLifeCycle(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    fun someError() {
        sendEvent(
            MainTranslatorScreenIntent.ShowWarningDialog(
                title = "Что-то пошло не так",
                description = "Не волнуйтесь, это просто ошибка"
            )
        )
    }

    fun closeWarning() {
        sendEvent(
            MainTranslatorScreenIntent.CloseWarningDialog
        )
    }

    private fun mergeSources(
        imageDetected: ImageDetected?,
        image: Image
    ): MainTranslatorScreenIntent {
        val bitmap = image.bitmap

        val coordinateClassification = imageDetected?.let {
            recognizeCoordinateUseCase(imageDetected)
        }
        val predictLetter = coordinateClassification?.label ?: ""
        val coordinate = imageDetected?.coordinates
        coordinateClassification?.run {
            if (this.percent > 30f) worldCompileManager.addLetter(this.label)
        }
        val currentWord = worldCompileManager.getWord()

        return MainTranslatorScreenIntent.ChangeAll(
            image = if (coordinate != null) bitmap.drawHand(coordinate) else bitmap,
            letter = predictLetter,
            textTranslation = currentWord
        )
    }

    private class MainTranslatorReducer(initial: MainTranslatorScreenState) :
        Reducer<MainTranslatorScreenState, MainTranslatorScreenIntent>(initial) {
        override fun reduce(
            oldState: MainTranslatorScreenState, intent: MainTranslatorScreenIntent
        ) {
            when (intent) {
                is MainTranslatorScreenIntent.OnImageChange -> {
                    setState(
                        oldState.copy(image = intent.newImage)
                    )
                }

                is MainTranslatorScreenIntent.OnRecognizedLetterChange -> {
                    setState(
                        oldState.copy(recognizedLetter = intent.letter)
                    )
                }

                is MainTranslatorScreenIntent.OnTextTranslatingChange -> {
                    setState(
                        oldState.copy(translatedText = intent.text)
                    )
                }

                is MainTranslatorScreenIntent.StartLoaderDialog -> {
                    setState(
                        oldState.copy(
                            showDialogLoader = true,
                            descriptionLoaderDialog = intent.description
                        )
                    )
                }

                MainTranslatorScreenIntent.StopLoaderDialog -> {
                    setState(
                        oldState.copy(showDialogLoader = false)
                    )
                }

                is MainTranslatorScreenIntent.ChangeAll -> {
                    setState(
                        oldState.copy(
                            image = intent.image,
                            recognizedLetter = intent.letter,
                            translatedText = intent.textTranslation
                        )
                    )
                }

                MainTranslatorScreenIntent.CloseWarningDialog -> {
                    setState(
                        oldState.copy(
                            showWarningDialog = false
                        )
                    )
                }

                is MainTranslatorScreenIntent.ShowWarningDialog -> {
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
