package com.ortin.gesturetranslator.app.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ortin.gesturetranslator.app.models.MainFrameState
import com.ortin.gesturetranslator.app.models.PredictState
import com.ortin.gesturetranslator.domain.managers.CameraInputManager
import com.ortin.gesturetranslator.domain.managers.MediaPipeManagerDomain
import com.ortin.gesturetranslator.domain.managers.WorldCompileManager
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var cameraManager: CameraInputManager,
    private var worldCompileManager: WorldCompileManager,
    private var mediaPipeManager: MediaPipeManagerDomain,
    private var recognizeCoordinateUseCase: RecognizeCoordinateUseCase
) : ViewModel() {
    private val _menuLiveData: MutableLiveData<MainFrameState> = MutableLiveData(MainFrameState())
    val menuLiveData: LiveData<MainFrameState> = _menuLiveData

    var predictLiveData: LiveData<PredictState> = MutableLiveData(PredictState())

    init {
        worldCompileManager.clearState()
    }

    fun startRealTimeImagining(lifecycleOwner: LifecycleOwner) {
        val predictState: Flow<PredictState> = combine(
            mediaPipeManager.flow,
            cameraManager.startListening(lifecycleOwner)
                .onEach { mediaPipeManager.detectLiveStream(it.bitmap) },
            ::mergeSources
        )

        predictLiveData = predictState.asLiveData()
    }

    private fun mergeSources(imageDetected: ImageDetected?, image: Image?): PredictState {
        val bitmap = image?.bitmap

        if (_menuLiveData.value?.realTimeButton == false) {
            return PredictState(
                imageFromCamera = bitmap,
                predictWord = worldCompileManager.getWord()
            )
        }

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

    override fun onCleared() {
        super.onCleared()
        offFlashLight()
        Timber.e("cleared [!]")
    }

    fun onFlashLight() {
        cameraManager.setStatusFlashlight(true)
        _menuLiveData.value = _menuLiveData.value?.copy(flashLight = true)
    }

    fun offFlashLight() {
        cameraManager.setStatusFlashlight(false)
        _menuLiveData.value = _menuLiveData.value?.copy(flashLight = false)
    }

    fun onStartRealTimeButton() {
        _menuLiveData.value = _menuLiveData.value?.copy(
            realTimeButton = true,
            bottomSheetBehavior = BottomSheetBehavior.STATE_COLLAPSED
        )
        worldCompileManager.clearState()
    }

    fun onStopRealTimeButton() {
        _menuLiveData.value = _menuLiveData.value?.copy(
            realTimeButton = false,
            bottomSheetBehavior = BottomSheetBehavior.STATE_EXPANDED
        )
    }

    fun onBottomSheetCollapsed() {
        onStartRealTimeButton()
    }

    fun onBottomSheetExpanded() {
        onStopRealTimeButton()
    }
}
