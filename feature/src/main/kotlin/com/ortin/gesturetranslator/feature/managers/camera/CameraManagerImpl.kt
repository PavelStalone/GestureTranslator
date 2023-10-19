package com.ortin.gesturetranslator.feature.managers.camera

import android.content.Context
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.ortin.gesturetranslator.feature.managers.camera.analyzers.BitmapAnalyzer
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class CameraManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cameraExecutor: ExecutorService,
    private val outputExecutor: Executor,
    private val cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
) : CameraManager {
    private var camera: Camera? = null

    override fun loadImage(
        cameraListener: CameraListener,
        lifecycleOwner: LifecycleOwner,
        cameraFacing: Int
    ) {
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
