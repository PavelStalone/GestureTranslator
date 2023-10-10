package com.ortin.gesturetranslator.domain.listeners

import com.ortin.gesturetranslator.domain.models.Image

interface LoadImagesListener {
    fun getImage(image: Image)

    fun error(exception: Exception)
}
