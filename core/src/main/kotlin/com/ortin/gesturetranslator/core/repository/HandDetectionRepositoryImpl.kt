package com.ortin.gesturetranslator.core.repository

import com.google.mediapipe.tasks.vision.core.RunningMode
import com.ortin.gesturetranslator.core.managers.mediapipe.HandLandmarkerHelper
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.Delegation
import com.ortin.gesturetranslator.domain.models.HandDetected
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.InputMode
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository

class HandDetectionRepositoryImpl(private val mediaPipeManager: MediaPipeManager) :
    HandDetectionRepository {
    override fun detectImage(image: Image) =
        mediaPipeManager.detectImage(image.mapToMPImageInput())

    override fun setDetectionHandListener(
        detectionHandListener: DetectionHandListener,
        settingsMediaPipe: SettingsMediaPipe
    ) =
        mediaPipeManager.setMPDetectionListener(
            detectionHandListener.mapToCoreListener(),
            settingsMediaPipe.mapToSettingsModel()
        )

    // Правила перевода для связи domain и core модулей
    private fun Image.mapToMPImageInput(): MPImageInput = MPImageInput(this.bitmap)

    private fun SettingsMediaPipe.mapToSettingsModel(): SettingsModel =
        SettingsModel(
            minHandDetectionConfidence = this.minHandDetectionConfidence,
            minHandTrackingConfidence = this.minHandTrackingConfidence,
            minHandPresenceConfidence = this.minHandPresenceConfidence,
            maxNumHands = this.maxNumHands,
            currentDelegate = when (this.currentDelegate) {
                Delegation.DELEGATE_CPU -> HandLandmarkerHelper.DELEGATE_CPU
                Delegation.DELEGATE_GPU -> HandLandmarkerHelper.DELEGATE_GPU
            },
            runningMode = when (this.runningMode) {
                InputMode.LIVE_STREAM -> RunningMode.LIVE_STREAM
                InputMode.IMAGE -> RunningMode.IMAGE
                InputMode.VIDEO -> RunningMode.VIDEO
            }
        )


    private fun MPDetection?.mapToCoreHandDetection(): HandDetected? {
        this ?: return null

        val coordinates = FloatArray(42)
        val result = this.results[0]

        if (result.landmarks().size != 0) {
            val iterator = result.landmarks()[0].iterator()

            for (i in coordinates.indices step 2) {
                if (iterator.hasNext()) {
                    val landmark = iterator.next()
                    coordinates[i] = landmark.x()
                    coordinates[i + 1] = landmark.y()
                } else {
                    break
                }
            }
        }
        return HandDetected(coordinates)
    }

    private fun DetectionHandListener.mapToCoreListener(): MPDetectionListener {
        return object : MPDetectionListener {
            override fun onResults(mpDetection: MPDetection?) =
                this@mapToCoreListener.detect(mpDetection.mapToCoreHandDetection())

            override fun onError(exception: Exception) = this@mapToCoreListener.error(exception)
        }
    }
}
