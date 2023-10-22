package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository
import javax.inject.Inject

class RecognizeImageUseCase @Inject constructor(private val recognizeImageRepository: RecognizeImageRepository) {
    fun execute(image: Image) {
        recognizeImageRepository.recogniseImage(image)
    }

    fun setOnRecogniseListener(recognizeImageListener: RecognizeImageListener) {
        recognizeImageRepository.setRecogniseListener(recognizeImageListener)
    }
}
