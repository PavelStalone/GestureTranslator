package com.ortin.gesturetranslator.app.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.HandDetected
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase
import com.ortin.gesturetranslator.domain.usecases.WordCompileUseCase
import com.ortin.gesturetranslator.models.MainFrameState
import com.ortin.gesturetranslator.models.PredictState
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
        detectHandUseCase.setOnDetectionHandListener(this)
    }


    private companion object {
        const val TAG = "MainViewModel"
    }

    @SuppressLint("DefaultLocale", "SetTextI18n", "CheckResult")
    override fun detect(handDetected: HandDetected?) {
        if (handDetected != null) {
            val coordinateClassification = recognizeCoordinateUseCase.execute(handDetected)

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
                    handDetected.coordinates
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
        val predictState = predictLiveData.value
        predictLiveData.value = PredictState(
            bitmap,
            predictState?.predictWord,
            predictState?.predictLetter,
            predictState?.coordinateHand
        )
        if (mainLiveData.value?.isRealtimeButton == true) detectHandUseCase.execute(image)
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
        val mainFrameState = mainLiveData.value
        mainLiveData.value = MainFrameState(
            true,
            mainFrameState!!.isRealtimeButton,
            mainFrameState.bottomSheetBehavior
        )
    }

    fun offFlashLight() {
        loadImageUseCase.setStatusFlashlight(false)
        val mainFrameState = mainLiveData.value
        mainLiveData.value =
            MainFrameState(
                false,
                mainFrameState!!.isRealtimeButton,
                mainFrameState.bottomSheetBehavior
            )
    }

    fun onStartRealTimeButton() {
        val mainFrameState = mainLiveData.value
        mainLiveData.value =
            MainFrameState(mainFrameState!!.isFlashlight, true, BottomSheetBehavior.STATE_COLLAPSED)
        val predictState = predictLiveData.value
        predictLiveData.value = PredictState(
            predictState!!.imageFromCamera,
            "",
            predictState.predictLetter,
            predictState.coordinateHand
        )
        wordCompileUseCase.clearState()
    }

    fun onStopRealTimeButton() {
        val mainFrameState = mainLiveData.value
        mainLiveData.value =
            MainFrameState(mainFrameState!!.isFlashlight, false, BottomSheetBehavior.STATE_EXPANDED)
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