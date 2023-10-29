package com.ortin.gesturetranslator.domain.managers

import androidx.lifecycle.LifecycleOwner
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener
import com.ortin.gesturetranslator.domain.models.CameraFacingSettings
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.repository.LoadImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraInputManager @Inject constructor(private val loadImageRepository: LoadImageRepository) {
    fun execute(
        lifecycleOwner: LifecycleOwner,
        cameraFacing: CameraFacingSettings = CameraFacingSettings.LENS_FACING_BACK
    ) = callbackFlow<Image> {
        val listener: LoadImagesListener = object : LoadImagesListener {
            override fun getImage(image: Image) {
                trySendBlocking(image)
            }

            override fun error(exception: Exception) {
                Timber.e(exception)
                close(exception)
            }
        }
        loadImageRepository.loadImages(listener, lifecycleOwner, cameraFacing)

        awaitClose()
    }.buffer(Channel.CONFLATED).flowOn(Dispatchers.IO)

    fun setStatusFlashlight(mode: Boolean) {
        loadImageRepository.setStatusFlashlight(mode)
    }
}
