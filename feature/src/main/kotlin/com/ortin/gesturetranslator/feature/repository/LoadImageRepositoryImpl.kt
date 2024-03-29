package com.ortin.gesturetranslator.feature.repository

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.CameraFacingSettings
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository
import com.ortin.gesturetranslator.feature.managers.camera.CameraManager
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener
import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadImageRepositoryImpl @Inject constructor(private val cameraManager: CameraManager) :
    LoadImageRepository {
    override fun loadImages(
        loadImagesListener: LoadImagesListener,
        lifecycleOwner: LifecycleOwner,
        cameraFacing: CameraFacingSettings
    ) {
        cameraManager.loadImage(
            cameraListener = loadImagesListener.mapToDomainListener(),
            lifecycleOwner = lifecycleOwner,
            cameraFacing = cameraFacing.mode
        )
    }

    override fun setStatusFlashlight(mode: Boolean) {
        cameraManager.setStatusFlashlight(mode)
    }

    // Translation rules for domain and feature modules
    private fun ImageFromCamera.mapToDomain(): Image = Image(this.image, this.rotaion)

    private fun LoadImagesListener.mapToDomainListener(): CameraListener =
        object : CameraListener {
            override fun getImage(imageFromCamera: ImageFromCamera) {
                this@mapToDomainListener.getImage(imageFromCamera.mapToDomain())
            }

            override fun error(exception: Exception) {
                this@mapToDomainListener.error(exception)
            }
        }
}
