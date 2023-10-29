package com.ortin.gesturetranslator.domain.usecases

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.CameraFacingSettings
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository
import javax.inject.Inject

class LoadImageUseCase @Inject constructor(private val loadImageRepository: LoadImageRepository) {
    fun execute(
        loadImagesListener: LoadImagesListener,
        lifecycleOwner: LifecycleOwner,
        cameraFacing: CameraFacingSettings = CameraFacingSettings.LENS_FACING_BACK
    ) {
        loadImageRepository.loadImages(loadImagesListener, lifecycleOwner, cameraFacing)
    }

    fun setStatusFlashlight(mode: Boolean) {
        loadImageRepository.setStatusFlashlight(mode)
    }
}
