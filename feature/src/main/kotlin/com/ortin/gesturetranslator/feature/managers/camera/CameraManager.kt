package com.ortin.gesturetranslator.feature.managers.camera

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener

interface CameraManager {
    fun loadImage(cameraListener: CameraListener, lifecycleOwner: LifecycleOwner, cameraFacing: Int)
    fun setStatusFlashlight(mode: Boolean)
}
