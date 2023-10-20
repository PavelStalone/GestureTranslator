package com.ortin.gesturetranslator.domain.usecases

import android.graphics.Bitmap
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.models.VideoDetected
import com.ortin.gesturetranslator.domain.models.VideoFileDecode
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository

class DetectHandUseCase(private val handDetectionRepository: HandDetectionRepository) {
    fun detectImage(image: Bitmap): ImageDetected? = handDetectionRepository.detectImage(image)

    fun detectVideoFile(videoFile: VideoFileDecode): VideoDetected? =
        handDetectionRepository.detectVideoFile(videoFile)

    fun detectLiveStream(image: Bitmap) {
        handDetectionRepository.detectLiveStream(image)
    }

    fun setSettingsModel(settingsMediaPipe: SettingsMediaPipe) {
        handDetectionRepository.setSettingsModel(settingsMediaPipe)
    }

    fun setMPDetectionListener(detectionHandListener: DetectionHandListener) {
        handDetectionRepository.setMPDetectionListener(detectionHandListener)
    }
}
