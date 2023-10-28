package com.ortin.gesturetranslator.app.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ortin.gesturetranslator.app.models.MainFrameState
import com.ortin.gesturetranslator.app.models.PredictState
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.managers.CameraInputManager
import com.ortin.gesturetranslator.domain.managers.MediaPipeManagerDomain
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.domain.usecases.WordCompileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var cameraManager: CameraInputManager,
    private var wordCompileUseCase: WordCompileUseCase,
    private var mediaPipeManager: MediaPipeManagerDomain,
    private var recognizeCoordinateUseCase: RecognizeCoordinateUseCase
) : ViewModel() {
    private val _mainLiveData: MutableLiveData<MainFrameState> = MutableLiveData()
    val mainLiveData = _mainLiveData as LiveData<MainFrameState>
    private val _predictLiveData: MutableLiveData<PredictState> = MutableLiveData()
    val predictLiveData = _predictLiveData as LiveData<PredictState>

    init {
        _mainLiveData.value = MainFrameState()
        _predictLiveData.value = PredictState()
        wordCompileUseCase.clearState()
    }

    fun startRealTimeImagining(lifecycleOwner: LifecycleOwner) {
        val predictState: Flow<PredictState> = combine(
            mediaPipeManager.flowMediaPipe,
            cameraManager.execute(lifecycleOwner).onEach { mediaPipeManager.detectLiveStream(it.bitmap) },
            ::mergeSources
        )

        viewModelScope.launch {
            predictState.collect {
                _predictLiveData.postValue(it)
            }
        }
    }

    private fun mergeSources(imageDetected: ImageDetected?, image: Image?,): PredictState {
        val bitmap = image?.bitmap
        bitmap?.run { mediaPipeManager.detectLiveStream(bitmap) }
        val coordinateClassification =
            imageDetected?.let { recognizeCoordinateUseCase.execute(imageDetected) }
        val predictLetter = coordinateClassification?.label ?: ""
        val coordinate = imageDetected?.coordinates
        coordinateClassification?.run {
            if (this.percent > 30f) wordCompileUseCase.addLetter(this.label)
        }
        val currentWord = wordCompileUseCase.getWord()

        return PredictState(
            imageFromCamera = bitmap,
            predictWord = currentWord,
            predictLetter = predictLetter,
            coordinateHand = coordinate
        )
    }

    override fun onCleared() {
        super.onCleared()
        offFlashLight()
        Timber.e("cleared [!]")
    }

    fun onFlashLight() {
        cameraManager.setStatusFlashlight(true)
        _mainLiveData.value = _mainLiveData.value?.copy(flashLight = true)
    }

    fun offFlashLight() {
        cameraManager.setStatusFlashlight(false)
        _mainLiveData.value = _mainLiveData.value?.copy(flashLight = false)
    }

    fun onStartRealTimeButton() {
        _mainLiveData.value = _mainLiveData.value?.copy(
            realTimeButton = true,
            bottomSheetBehavior = BottomSheetBehavior.STATE_COLLAPSED
        )
        _predictLiveData.value = _predictLiveData.value?.copy(predictWord = "")
        wordCompileUseCase.clearState()
    }

    fun onStopRealTimeButton() {
        _mainLiveData.value = _mainLiveData.value?.copy(
            realTimeButton = true,
            bottomSheetBehavior = BottomSheetBehavior.STATE_EXPANDED
        )
    }

    fun bottomSheetCollapsed() {
        onStartRealTimeButton()
    }

    fun bottomSheetExpanded() {
        onStopRealTimeButton()
    }
}
