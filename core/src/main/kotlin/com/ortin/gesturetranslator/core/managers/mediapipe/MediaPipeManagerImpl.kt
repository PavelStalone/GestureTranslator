package com.ortin.gesturetranslator.core.managers.mediapipe

import android.content.Context
import android.os.SystemClock
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker.HandLandmarkerOptions
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput

class MediaPipeManagerImpl(val context: Context, private val modelPath: String) : MediaPipeManager {
    private var mpDetectionListener: MPDetectionListener? = null
    private var handLandmarker: HandLandmarker? = null

    override fun detectImage(mpImageInput: MPImageInput) {
        handLandmarker ?: setupBuilder()
        handLandmarker?.detectAsync(mpImageInput.mpImage, SystemClock.uptimeMillis())
    }

    override fun setMPDetectionListener(mpDetectionListener: MPDetectionListener) {
        this.mpDetectionListener = mpDetectionListener
    }

    private fun setupBuilder() {
        val baseOptionsBuilder = BaseOptions.builder().setModelAssetPath(modelPath)
        val baseOptions = baseOptionsBuilder.build()
        val optionsBuilder = HandLandmarkerOptions.builder()
            .setBaseOptions(baseOptions)
            .setMinHandDetectionConfidence(0.5f) // Минимальная оценка достоверности для обнаружения рук, которая считается успешной в модели обнаружения ладоней
            .setMinTrackingConfidence(0.5f) // Минимальная оценка достоверности для того, чтобы отслеживание рук считалось успешным
            .setMinHandPresenceConfidence(0.5f) // Минимальная оценка достоверности для оценки присутствия руки в модели обнаружения ориентира руки
            .setNumHands(1) // Максимальное количество рук, обнаруженных Детектором ориентиров рук
            .setRunningMode(RunningMode.LIVE_STREAM) // Устанавливает режим работы для задачи ручного ориентира
            .setResultListener(this::resultDetection)
            .setErrorListener(this::errorDetection)
        val options = optionsBuilder.build()

        handLandmarker = HandLandmarker.createFromOptions(context, options)
    }

    private fun resultDetection(result: HandLandmarkerResult, mpImage: MPImage) {
        mpDetectionListener?.let {
            if (result.landmarks().size == 0) it.detect(null)
            else it.detect(MPDetection(result, mpImage))
        }
    }

    private fun errorDetection(exception: RuntimeException) {
        mpDetectionListener?.error(exception)
    }
}
