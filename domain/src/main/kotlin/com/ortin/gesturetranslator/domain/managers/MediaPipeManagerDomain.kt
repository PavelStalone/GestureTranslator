package com.ortin.gesturetranslator.domain.managers

import android.graphics.Bitmap
import android.util.Log
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.models.VideoDetected
import com.ortin.gesturetranslator.domain.models.VideoFileDecode
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPipeManagerDomain @Inject constructor(private val handDetectionRepository: HandDetectionRepository) {
    val flowMediaPipe = MutableStateFlow<ImageDetected?>(null)

    init {
        handDetectionRepository.setMPDetectionListener(object : DetectionHandListener {
            override fun detect(imageDetected: ImageDetected?) {
                flowMediaPipe.value = imageDetected
            }

            override fun error(exception: Exception) {
                Timber.e(exception)
            }
        })
    }

    fun detectLiveStream(image: Bitmap) {
        handDetectionRepository.detectLiveStream(image)
    }

    fun setSettingsModel(settingsMediaPipe: SettingsMediaPipe) {
        handDetectionRepository.setSettingsModel(settingsMediaPipe)
    }

    fun detectImage(image: Bitmap) =
        handDetectionRepository.detectImage(image)

    suspend fun detectVideoFile(videoFile: VideoFileDecode): VideoDetected? =
        handDetectionRepository.detectVideoFile(videoFile)
}