package com.ortin.gesturetranslator.feature.managers.camera.analyzers

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera

class BitmapAnalyzer(
    private val cameraFacing: Int,
    private val listener: (image: ImageFromCamera) -> Unit
) : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        val bitmapBuffer =
            Bitmap.createBitmap(
                image.width,
                image.height,
                Bitmap.Config.ARGB_8888
            )
        image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }

        val matrix = Matrix().apply {
            postRotate(image.imageInfo.rotationDegrees.toFloat())

            if (cameraFacing == CameraSelector.LENS_FACING_FRONT) {
                postScale(
                    -1f,
                    1f,
                    image.width.toFloat(),
                    image.height.toFloat()
                )
            }
        }
        val rotatedBitmap = Bitmap.createBitmap(
            bitmapBuffer, 0, 0, bitmapBuffer.width, bitmapBuffer.height,
            matrix, true
        )

        listener.invoke(
            ImageFromCamera(
                rotatedBitmap,
                image.imageInfo.rotationDegrees
            )
        )
    }
}
