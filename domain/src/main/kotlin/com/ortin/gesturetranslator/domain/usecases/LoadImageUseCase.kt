package com.ortin.gesturetranslator.domain.usecases

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository

class LoadImageUseCase(private val loadImageRepository: LoadImageRepository) {
    fun execute(loadImagesListener: LoadImagesListener, lifecycleOwner: LifecycleOwner) {
        loadImageRepository.loadImages(loadImagesListener, lifecycleOwner)
    }

    fun setStatusFlashlight(mode: Boolean) {
        loadImageRepository.setStatusFlashlight(mode)
    }
}