package com.ortin.gesturetranslator.feature.managers.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.AspectRatio
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener
import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera
import com.ortin.gesturetranslator.feature.translators.YUVtoRGB

@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
class CameraManagerImpl(private val context: Context) : CameraManager {
    private var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>? = null
    private val translator: YUVtoRGB = YUVtoRGB()

    private lateinit var bitmap: Bitmap
    private var camera: Camera? = null
    override fun  loadImage(cameraListener: CameraListener?, lifecycleOwner: LifecycleOwner?) {
        cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture!!.addListener({

            try {
                val cameraProvider = cameraProviderFuture!!.get()
                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context)) { image: ImageProxy ->

                    val img = image.image
                    bitmap = img?.let { translator.translateYUV(it, context) }!!

                    val matrix = Matrix()
                    matrix.postRotate(image.imageInfo.rotationDegrees.toFloat())
                    // !! Это нужно проверить, не уверен, что сделал правильно)
                    bitmap = Bitmap.createBitmap(
                        bitmap!!,
                        0,
                        0,
                        bitmap!!.width,
                        bitmap!!.height,
                        matrix,
                        true
                    )
                    val imageFromCamera =
                        ImageFromCamera(bitmap, image.imageInfo.rotationDegrees)

                    cameraListener?.getImage(imageFromCamera)
                    image.close()
                }
                camera =
                    lifecycleOwner?.let { cameraProvider.bindToLifecycle(it, cameraSelector, imageAnalysis) }
            } catch (e: Exception) {
                cameraListener?.error(e)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    override fun setStatusFlashlight(mode: Boolean) {
        if (camera != null) {
            camera!!.cameraControl.enableTorch(mode)
        }
    }
}