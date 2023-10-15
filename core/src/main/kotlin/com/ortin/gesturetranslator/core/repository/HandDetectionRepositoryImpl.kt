package com.ortin.gesturetranslator.core.repository

import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.ortin.gesturetranslator.core.managers.mediapipe.MediaPipeManager
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.HandDetected
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository
import java.lang.Exception

class HandDetectionRepositoryImpl(private val mediaPipeManager: MediaPipeManager) :
    HandDetectionRepository {
    override fun detectImage(image: Image) =
        mediaPipeManager.detectImage(mapToMPImageInput(image))

    override fun setDetectionHandListener(detectionHandListener: DetectionHandListener) =
        mediaPipeManager.setMPDetectionListener(mapToCoreListener(detectionHandListener))

    // Правила перевода для связи domain и core модулей
    private fun mapToMPImageInput(image: Image): MPImageInput =
        MPImageInput(BitmapImageBuilder(image.bitmap).build())

    private fun mapToCoreHandDetection(mpDetection: MPDetection?): HandDetected? {
        mpDetection ?: return null

        val coordinates = MutableList(42) {0f}
        val result = mpDetection.result

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

    private fun mapToCoreListener(detectionHandListener: DetectionHandListener): MPDetectionListener {
        return object : MPDetectionListener {
            override fun detect(mpDetection: MPDetection?) {
                detectionHandListener.detect(mapToCoreHandDetection(mpDetection))
            }

            override fun error(exception: Exception?) =
                detectionHandListener.error(exception)
        }
    }
}
