package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.HandDetectionRepository

class DetectHandUseCase(private val handDetectionRepository: HandDetectionRepository) {
    fun execute(image: Image) {
        handDetectionRepository.detectImage(image)
    }

    fun setOnDetectionHandListener(detectionHandListener: DetectionHandListener) {
        handDetectionRepository.setDetectionHandListener(detectionHandListener)
    }
}
