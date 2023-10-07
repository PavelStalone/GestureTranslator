package com.ortin.gesturetranslator.domain.repository

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener

interface LoadImageRepository {
    fun loadImages(loadImagesListener: LoadImagesListener, lifecycleOwner: LifecycleOwner)

    fun setStatusFlashlight(mode: Boolean)
}
