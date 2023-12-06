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
import com.ortin.gesturetranslator.domain.models.SettingsDomain
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.main.MainTranslatorScreenIntent
import com.ortin.gesturetranslator.main.MainTranslatorScreenState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainTranslatorViewModel @Inject constructor(
    private val cameraManager: CameraInputManager,
    private val worldCompileManager: WorldCompileManager,
    private val mediaPipeManager: MediaPipeManagerDomain,
    private val recognizeCoordinateUseCase: RecognizeCoordinateUseCase,
    private val settingsManager: SettingsManager
) : ViewModel<MainTranslatorScreenState, MainTranslatorScreenIntent>() {

    private val reducer = MainTranslatorReducer(MainTranslatorScreenState.initial())
    override val state: Flow<MainTranslatorScreenState>
        get() = reducer.state

    private lateinit var lifecycleOwner: LifecycleOwner
    private var oldMediaPipeSettings: SettingsDomain = SettingsDomain()
    private lateinit var coroutineContext: CoroutineContext

    private fun sendEvent(event: MainTranslatorScreenIntent) {
        reducer.sendIntent(event)
    }

    private fun isSettingsChanged(): Boolean {
        if (oldMediaPipeSettings == settingsManager.getSettings()) return false
        return true
    }

    private suspend fun changeMediaPipeSettings() {
        mediaPipeManager.setSettingsModel(
            SettingsMediaPipe(
                currentDelegate = if (oldMediaPipeSettings.gpu) {
                    SettingsMediaPipe.Delegation.DELEGATE_GPU
                } else {
                    SettingsMediaPipe.Delegation.DELEGATE_CPU
                }
            )
        )
    }

    private fun startTranslating() {
        coroutineContext = viewModelScope.launch {
            cameraManager.startListening(lifecycleOwner)
                .onEach { mediaPipeManager.detectLiveStream(it.bitmap) }.collect()

            val intentFlow: Flow<MainTranslatorScreenIntent> = combine(
                mediaPipeManager.flow,
                cameraManager.startListening(lifecycleOwner)
                    .onEach { mediaPipeManager.detectLiveStream(it.bitmap) },
                ::mergeSources
            )
        }
    }

    private fun stopTranslating() {
        coroutineContext.cancel()
    }

    fun onTranslatingStatusChanged(status: Boolean) {
        if (status) {
            if (isSettingsChanged()) {
                viewModelScope.launch {
                    sendEvent(
                        MainTranslatorScreenIntent.StartLoaderDialog(
                            description = "Идет настройка переводчика"
                        )
                    )

                    oldMediaPipeSettings = settingsManager.getSettings()
                    changeMediaPipeSettings()
                }
            } else {
                startTranslating()
            }
        } else {
            stopTranslating()
        }
    }

    fun onTextCorrectedStatusChanged(status: Boolean) {

    }

    fun bindLifeCycle(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    private fun mergeSources(
        imageDetected: ImageDetected?,
        image: Image?
    ): MainTranslatorScreenIntent {
        val bitmap = image?.bitmap

        val coordinateClassification = imageDetected?.let {
            recognizeCoordinateUseCase(imageDetected)
        }
        val predictLetter = coordinateClassification?.label ?: ""
        val coordinate = imageDetected?.coordinates
        coordinateClassification?.run {
            if (this.percent > 30f) worldCompileManager.addLetter(this.label)
        }
        val currentWord = worldCompileManager.getWord()



        return PredictState(
            imageFromCamera = bitmap,
            predictWord = currentWord,
            predictLetter = predictLetter,
            coordinateHand = coordinate
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
                        oldState.copy(showDialogLoader = true, descriptionLoaderDialog = intent.description)
                    )
                }

                MainTranslatorScreenIntent.StopLoaderDialog -> {
                    setState(
                        oldState.copy(showDialogLoader = false)
                    )
                }
            }
        }
    }
}
