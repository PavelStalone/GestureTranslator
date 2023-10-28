package com.ortin.gesturetranslator.app.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ortin.gesturetranslator.app.models.MainFrameState
import com.ortin.gesturetranslator.app.models.PredictState
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.managers.MediaPipeManagerDomain
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.domain.usecases.WordCompileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var loadImageUseCase: LoadImageUseCase,
    private var wordCompileUseCase: WordCompileUseCase,
    private var mediaPipeManager: MediaPipeManagerDomain,
    private var recognizeCoordinateUseCase: RecognizeCoordinateUseCase
) : ViewModel(), LoadImagesListener, DetectionHandListener {
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
        loadImageUseCase.execute(this, lifecycleOwner)
        viewModelScope.launch {
            mediaPipeManager.listenerResult.collect {
                detect(it)
            }
        }
    }

    private companion object {
        const val TAG = "MainViewModel"
    }

    @SuppressLint("DefaultLocale", "SetTextI18n", "CheckResult")
    override fun detect(imageDetected: ImageDetected?) {
        if (imageDetected != null) {
            val coordinateClassification = recognizeCoordinateUseCase.execute(imageDetected)

            if (coordinateClassification.percent > 30f) {
                wordCompileUseCase.addLetter(coordinateClassification.label)
            }

            val predictLetter = coordinateClassification.label

            val predictState = _predictLiveData.value
            viewModelScope.launch {
                _predictLiveData.value = PredictState(
                    predictState?.imageFromCamera,
                    wordCompileUseCase.getWord(),
                    predictLetter,
                    imageDetected.coordinates
                )
            }
        } else {
            val predictState = _predictLiveData.value
            viewModelScope.launch {
                _predictLiveData.value = PredictState(
                    predictState?.imageFromCamera,
                    predictState?.predictWord ?: "",
                    "",
                    null
                )
            }
        }
    }

    override fun getImage(image: Image) {
        val bitmap = image.bitmap
        _predictLiveData.value = _predictLiveData.value?.copy(imageFromCamera = bitmap)
        if (_mainLiveData.value?.realTimeButton == true) mediaPipeManager.detectLiveStream(bitmap)
    }

    override fun error(exception: Exception) {
        Log.e(TAG, "Error!  [!]")
        exception.printStackTrace()
    }

    override fun onCleared() {
        super.onCleared()
        offFlashLight()
        Log.e(TAG, "cleared [!]")
    }

    fun onFlashLight() {
        loadImageUseCase.setStatusFlashlight(true)
        _mainLiveData.value = _mainLiveData.value?.copy(flashLight = true)
    }

    fun offFlashLight() {
        loadImageUseCase.setStatusFlashlight(false)
        _mainLiveData.value = _mainLiveData.value?.copy(flashLight = false)
    }

    fun onStartRealTimeButton() {
        _mainLiveData.value = _mainLiveData.value?.copy(realTimeButton = true, bottomSheetBehavior = BottomSheetBehavior.STATE_COLLAPSED)
        _predictLiveData.value = _predictLiveData.value?.copy(predictWord = "")
        wordCompileUseCase.clearState()
    }

    fun onStopRealTimeButton() {
        _mainLiveData.value = _mainLiveData.value?.copy(realTimeButton = true, bottomSheetBehavior = BottomSheetBehavior.STATE_EXPANDED)
    }

    fun bottomSheetCollapsed() {
        onStartRealTimeButton()
    }

    fun bottomSheetExpanded() {
        onStopRealTimeButton()
    }
}
