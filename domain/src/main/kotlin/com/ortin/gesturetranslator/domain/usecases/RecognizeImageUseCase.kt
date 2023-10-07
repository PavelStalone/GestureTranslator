package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository

class RecognizeImageUseCase(private val recognizeImageRepository: RecognizeImageRepository) {
    fun execute(image: Image?) {
        image?.let {recognizeImageRepository.recogniseImage(it)}
    }

    fun setOnRecogniseListener(recognizeImageListener: RecognizeImageListener?) {
        recognizeImageListener?.let {recognizeImageRepository.setRecogniseListener(it)}
    }
}