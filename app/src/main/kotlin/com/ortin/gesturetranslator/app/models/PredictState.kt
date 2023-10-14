package com.ortin.gesturetranslator.app.models

import android.graphics.Bitmap

class PredictState {
    var imageFromCamera: Bitmap? = null
        private set
    var predictWord = ""
        private set
    var predictLetter = ""
        private set
    var coordinateHand: FloatArray? = null
        private set

    constructor()
    constructor(
        imageFromCamera: Bitmap?,
        predictWord: String,
        predictLetter: String,
        coordinateHand: FloatArray?
    ) {
        this.imageFromCamera = imageFromCamera
        this.predictWord = predictWord
        this.predictLetter = predictLetter
        this.coordinateHand = coordinateHand
    }
}
