package com.ortin.gesturetranslator.main.components

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import com.ortin.gesturetranslator.ui.theme.purple

object PaintHand {
    private var pointPaint: Paint = Paint().apply {
        color = purple.toArgb()
        isAntiAlias = true
        strokeWidth = 5f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    fun Bitmap.drawHand(coordinates: List<Float>): Bitmap {
        val drawCanvas = Canvas(this)

        for (i in coordinates.indices step 2) {
            drawCanvas.drawCircle(
                coordinates[i] * this.width,
                coordinates[i + 1] * this.height,
                4f,
                pointPaint
            )
        }

        return this
    }
}
