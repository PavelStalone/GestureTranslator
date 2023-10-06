package com.ortin.gesturetranslator.feature.managers.camera

import android.content.Context
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.ortin.gesturetranslator.feature.managers.camera.analyzers.BitmapAnalyzer
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraManagerImpl(
    private val context: Context,
    private val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor(),
    private val outputExecutor: Executor = ContextCompat.getMainExecutor(context)
) : CameraManager {
    private var camera: Camera? = null

    override fun loadImage(
        cameraListener: CameraListener,
        lifecycleOwner: LifecycleOwner,
        cameraFacing: Int
    ) {
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
            ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            try {
                val cameraProvider = cameraProviderFuture.get()
                    ?: throw IllegalStateException("Camera initialization failed")

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(cameraFacing)
                    .build()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setResolutionSelector(
                        ResolutionSelector.Builder()
                            .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                            .build()
                    )
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, BitmapAnalyzer(cameraFacing) { image ->
                            outputExecutor.execute {
                                cameraListener.getImage(image)
                            }
                        })
                    }

                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    imageAnalysis
                )
            } catch (e: Exception) {
                cameraListener.error(e)
            }
        }, outputExecutor)
    }

    override fun setStatusFlashlight(mode: Boolean) {
        camera?.cameraControl?.enableTorch(mode)
    }
}
