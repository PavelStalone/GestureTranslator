package com.ortin.gesturetranslator.app.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ortin.gesturetranslator.app.models.MainFrameState
import com.ortin.gesturetranslator.app.models.PredictState
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase
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
    private var detectHandUseCase: DetectHandUseCase,
    private var recognizeCoordinateUseCase: RecognizeCoordinateUseCase
) : LoadImagesListener, DetectionHandListener, ViewModel() {
    private val mainLiveData: MutableLiveData<MainFrameState> = MutableLiveData()
    private val predictLiveData: MutableLiveData<PredictState> = MutableLiveData()
    init {
        mainLiveData.value = MainFrameState()
        predictLiveData.value = PredictState()
        wordCompileUseCase.clearState()
    }

    fun startRealTimeImagining(lifecycleOwner: LifecycleOwner) {
        loadImageUseCase.execute(this, lifecycleOwner)
        detectHandUseCase.setMPDetectionListener(this)
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

            val predictState = predictLiveData.value
            CoroutineScope(Dispatchers.Main).launch {
                predictLiveData.value = PredictState(
                    predictState?.imageFromCamera,
                    wordCompileUseCase.getWord(),
                    predictLetter,
                    imageDetected.coordinates
                )
            }
        } else {
            val predictState = predictLiveData.value
            CoroutineScope(Dispatchers.Main).launch {
                predictLiveData.value = PredictState(
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
        predictLiveData.value = predictLiveData.value?.copy(imageFromCamera = bitmap)
        if (mainLiveData.value?.realTimeButton == true) detectHandUseCase.detectLiveStream(bitmap)
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
        mainLiveData.value = mainLiveData.value?.copy(flashLight = true)
    }

    fun offFlashLight() {
        loadImageUseCase.setStatusFlashlight(false)
        mainLiveData.value = mainLiveData.value?.copy(flashLight = false)
    }

    fun onStartRealTimeButton() {
        mainLiveData.value = mainLiveData.value?.copy(realTimeButton = true, bottomSheetBehavior = BottomSheetBehavior.STATE_COLLAPSED)
        predictLiveData.value = predictLiveData.value?.copy(predictWord = "")
        wordCompileUseCase.clearState()
    }

    fun onStopRealTimeButton() {
        mainLiveData.value = mainLiveData.value?.copy(realTimeButton = true, bottomSheetBehavior = BottomSheetBehavior.STATE_EXPANDED)
    }

    fun bottomSheetCollapsed() {
        onStartRealTimeButton()
    }

    fun bottomSheetExpanded() {
        onStopRealTimeButton()
    }

    fun getMainLiveData(): LiveData<MainFrameState> {
        return mainLiveData
    }

    fun getPredictLiveData(): LiveData<PredictState> {
        return predictLiveData
    }
}
