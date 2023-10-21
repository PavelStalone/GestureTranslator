package com.ortin.gesturetranslator.app.models

import android.graphics.Bitmap

data class PredictState(
    val imageFromCamera: Bitmap? = null,
    val predictWord: String = "",
    val predictLetter: String = "",
    val coordinateHand: List<Float>? = null
)
