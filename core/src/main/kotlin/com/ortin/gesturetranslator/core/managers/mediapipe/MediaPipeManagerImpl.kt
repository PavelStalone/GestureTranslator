package com.ortin.gesturetranslator.core.managers.mediapipe

import android.content.Context
import android.graphics.Bitmap
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoInput
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel

class MediaPipeManagerImpl(val context: Context, modelPath: String) : MediaPipeManager {
    private var handLandmarkerHelper: HandLandmarkerHelper

    init {
        handLandmarkerHelper = HandLandmarkerHelper(
            context = context,
            modelPath = modelPath
        )
    }

    override fun detectImage(image: Bitmap): MPImageDetection? =
        handLandmarkerHelper.detectImage(image)

    override fun detectVideoFile(videoFile: MPVideoInput): MPVideoDetection? =
        handLandmarkerHelper.detectVideoFile(videoFile.videoUri, videoFile.inferenceIntervalMs)

    override fun detectLiveStream(image: Bitmap) =
        handLandmarkerHelper.detectLiveStream(image)

    override fun setSettingsModel(settingsModel: SettingsModel) {
        handLandmarkerHelper.settingsModel = settingsModel
        handLandmarkerHelper.update()
    }

    override fun setMPDetectionListener(mpDetectionListener: MPDetectionListener) {
        handLandmarkerHelper.handLandmarkerHelperListener = mpDetectionListener
        handLandmarkerHelper.update()
    }

    private fun HandLandmarkerHelper.update() {
        this.clearHandLandmarker()
        this.setupHandLandmarker()
    }
}
