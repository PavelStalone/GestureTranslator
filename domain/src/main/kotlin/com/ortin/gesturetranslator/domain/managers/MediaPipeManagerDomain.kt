package com.ortin.gesturetranslator.domain.managers

import android.graphics.Bitmap
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.models.VideoDetected
import com.ortin.gesturetranslator.domain.models.VideoFileDecode
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPipeManagerDomain @Inject constructor(private val handDetectionRepository: HandDetectionRepository) :
    FlowManager<ImageDetected?>(null) {
    init {
        handDetectionRepository.setMPDetectionListener(object : DetectionHandListener {
            override fun detect(imageDetected: ImageDetected?) {
                _flow.value = imageDetected
            }

            override fun error(exception: Exception) {
                Timber.e(exception)
            }
        })
    }

    fun detectLiveStream(image: Bitmap) {
        handDetectionRepository.detectLiveStream(image)
    }

    suspend fun setSettingsModel(settingsMediaPipe: SettingsMediaPipe) {
        handDetectionRepository.setSettingsModel(settingsMediaPipe)
    }

    fun detectImage(image: Bitmap) =
        handDetectionRepository.detectImage(image)

    suspend fun detectVideoFile(videoFile: VideoFileDecode): VideoDetected? =
        handDetectionRepository.detectVideoFile(videoFile)
}
