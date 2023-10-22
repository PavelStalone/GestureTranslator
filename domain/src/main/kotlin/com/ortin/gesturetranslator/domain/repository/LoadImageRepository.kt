package com.ortin.gesturetranslator.domain.repository

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.CameraFacingSettings

interface LoadImageRepository {
    fun loadImages(
        loadImagesListener: LoadImagesListener,
        lifecycleOwner: LifecycleOwner,
        cameraFacing: CameraFacingSettings
    )

    fun setStatusFlashlight(mode: Boolean)
}
