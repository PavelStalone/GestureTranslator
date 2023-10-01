package com.ortin.gesturetranslator.feature.managers.camera.listeners

import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera

interface CameraListener {
    fun getImage(imageFromCamera: ImageFromCamera?)
    fun error(exception: Exception?)
}
