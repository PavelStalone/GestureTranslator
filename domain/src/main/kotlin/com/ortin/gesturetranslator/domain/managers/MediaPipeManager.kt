package com.ortin.gesturetranslator.domain.managers

import android.graphics.Bitmap
import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.models.VideoDetected
import com.ortin.gesturetranslator.domain.models.VideoFileDecode
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPipeManager @Inject constructor(private val handDetectionRepository: HandDetectionRepository) {
    private val listenerResult = callbackFlow<ImageDetected?> {
        val listener: DetectionHandListener = object : DetectionHandListener {
            override fun detect(imageDetected: ImageDetected?) {
                trySend(imageDetected)
            }

            override fun error(exception: Exception) {
                Timber.e(exception)
                close(exception)
            }
        }
        handDetectionRepository.setMPDetectionListener(listener)

        awaitClose()
    }.flowOn(Dispatchers.IO)

    fun detectLiveStream(image: Bitmap) {
        handDetectionRepository.detectLiveStream(image)
    }

    fun setSettingsModel(settingsMediaPipe: SettingsMediaPipe) {
        handDetectionRepository.setSettingsModel(settingsMediaPipe)
    }

    suspend fun detectImage(image: Bitmap): ImageDetected? =
        handDetectionRepository.detectImage(image)

    suspend fun detectVideoFile(videoFile: VideoFileDecode): VideoDetected? =
        handDetectionRepository.detectVideoFile(videoFile)
}