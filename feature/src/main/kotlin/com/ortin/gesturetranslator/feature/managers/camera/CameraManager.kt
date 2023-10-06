package com.ortin.gesturetranslator.feature.managers.camera

import androidx.camera.core.CameraSelector
import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener

interface CameraManager {
    fun loadImage(cameraListener: CameraListener, lifecycleOwner: LifecycleOwner, cameraFacing: Int = CameraSelector.LENS_FACING_BACK)
    fun setStatusFlashlight(mode: Boolean)
}
