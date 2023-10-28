package com.ortin.gesturetranslator.core.repository

import android.graphics.Bitmap
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.ortin.gesturetranslator.core.managers.mediapipe.HandLandmarkerHelper
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoInput
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel
import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.di.DoDispatchers
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.models.VideoDetected
import com.ortin.gesturetranslator.domain.models.VideoFileDecode
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HandDetectionRepositoryImpl @Inject constructor(
    private val mediaPipeManager: MediaPipeManager,
    @Dispatcher(DoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : HandDetectionRepository {
    override fun detectImage(image: Bitmap): ImageDetected? =
        mediaPipeManager.detectImage(image).mapToCoreImageDetected()


    override suspend fun detectVideoFile(videoFile: VideoFileDecode): VideoDetected? =
        withContext(ioDispatcher) {
            mediaPipeManager.detectVideoFile(videoFile.mapToCoreMPVideoInput()).mapToVideDetected()
        }

    override fun detectLiveStream(image: Bitmap) {
        mediaPipeManager.detectLiveStream(image)
    }

    override fun setSettingsModel(settingsMediaPipe: SettingsMediaPipe) {
        mediaPipeManager.setSettingsModel(settingsMediaPipe.mapToSettingsModel())
    }

    override fun setMPDetectionListener(detectionHandListener: DetectionHandListener) {
        mediaPipeManager.setMPDetectionListener(detectionHandListener.mapToCoreListener())
    }

    // Правила перевода для связи domain и core модулей
    private fun MPImageDetection?.mapToCoreImageDetected(): ImageDetected? {
        if (this == null) return null

        val coordinates = MutableList(42) { 0f }
        val result = this.result

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
        } else {
            return null
        }

        return ImageDetected(coordinates)
    }

    private fun MPVideoDetection?.mapToVideDetected(): VideoDetected? {
        if (this == null) return null

        val results = MutableList<ImageDetected?>(this.results.size) { null }

        this.results.forEachIndexed { index, handLandmarkerResult ->
            if (handLandmarkerResult.landmarks().size != 0) {
                val coordinates = MutableList(42) { 0f }
                val iterator = handLandmarkerResult.landmarks()[0].iterator()

                for (i in coordinates.indices step 2) {
                    if (iterator.hasNext()) {
                        val landmark = iterator.next()
                        coordinates[i] = landmark.x()
                        coordinates[i + 1] = landmark.y()
                    } else {
                        break
                    }
                }

                results[index] = ImageDetected(coordinates)
            } else {
                results[index] = null
            }
        }

        return VideoDetected(
            results = results,
            inferenceTime = this.inferenceTime,
            inputImageHeight = this.inputImageHeight,
            inputImageWidth = this.inputImageWidth
        )
    }

    private fun VideoFileDecode.mapToCoreMPVideoInput(): MPVideoInput =
        MPVideoInput(this.uri, this.inferenceIntervalMs)

    private fun SettingsMediaPipe.mapToSettingsModel(): SettingsModel =
        SettingsModel(
            minHandDetectionConfidence = this.minHandDetectionConfidence,
            minHandTrackingConfidence = this.minHandTrackingConfidence,
            minHandPresenceConfidence = this.minHandPresenceConfidence,
            maxNumHands = this.maxNumHands,
            currentDelegate = when (this.currentDelegate) {
                SettingsMediaPipe.Delegation.DELEGATE_CPU -> HandLandmarkerHelper.DELEGATE_CPU
                SettingsMediaPipe.Delegation.DELEGATE_GPU -> HandLandmarkerHelper.DELEGATE_GPU
            },
            runningMode = when (this.runningMode) {
                SettingsMediaPipe.InputMode.LIVE_STREAM -> RunningMode.LIVE_STREAM
                SettingsMediaPipe.InputMode.IMAGE -> RunningMode.IMAGE
                SettingsMediaPipe.InputMode.VIDEO -> RunningMode.VIDEO
            }
        )

    private fun DetectionHandListener.mapToCoreListener(): MPDetectionListener {
        return object : MPDetectionListener {
            override fun onResults(mpImageDetection: MPImageDetection?) =
                this@mapToCoreListener.detect(mpImageDetection.mapToCoreImageDetected())

            override fun onError(exception: Exception) = this@mapToCoreListener.error(exception)
        }
    }
}
