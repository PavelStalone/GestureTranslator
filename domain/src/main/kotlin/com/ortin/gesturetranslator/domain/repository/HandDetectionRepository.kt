package com.ortin.gesturetranslator.domain.repository

import android.graphics.Bitmap
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.models.SettingsMediaPipe
import com.ortin.gesturetranslator.domain.models.VideoDetected
import com.ortin.gesturetranslator.domain.models.VideoFileDecode

interface HandDetectionRepository {
    fun detectImage(image: Bitmap): ImageDetected?
    suspend fun detectVideoFile(videoFile: VideoFileDecode): VideoDetected?
    fun detectLiveStream(image: Bitmap)
    suspend fun setSettingsModel(settingsMediaPipe: SettingsMediaPipe)
    fun setMPDetectionListener(detectionHandListener: DetectionHandListener)
}
