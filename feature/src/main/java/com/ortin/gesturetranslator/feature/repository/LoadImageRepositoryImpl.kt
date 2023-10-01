package com.ortin.gesturetranslator.feature.repository

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository
import com.ortin.gesturetranslator.feature.managers.camera.CameraManager
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener
import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera

class LoadImageRepositoryImpl(private val cameraManager: CameraManager) : LoadImageRepository {
    override fun loadImages(
        loadImagesListener: LoadImagesListener,
        lifecycleOwner: LifecycleOwner
    ) {
        cameraManager.loadImage(mapperToDomainListener(loadImagesListener), lifecycleOwner)
    }

    override fun setStatusFlashlight(mode: Boolean) {
        cameraManager.setStatusFlashlight(mode)
    }

    // Translation rules for domain and feature modules
    private fun mapToDomain(imageFromCamera: ImageFromCamera): Image {
        return Image(imageFromCamera.image, imageFromCamera.rotaion)
    }

    private fun mapperToDomainListener(loadImagesListener: LoadImagesListener): CameraListener {
        return object : CameraListener {
            override fun getImage(imageFromCamera: ImageFromCamera) {
                loadImagesListener.getImage(mapToDomain(imageFromCamera))
            }

            override fun error(exception: Exception) {
                loadImagesListener.error(exception)
            }
        }
    }
}