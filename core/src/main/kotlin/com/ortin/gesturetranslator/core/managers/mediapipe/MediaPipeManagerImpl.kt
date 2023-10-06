package com.ortin.gesturetranslator.core.managers.mediapipe

import android.content.Context
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageInput
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel

class MediaPipeManagerImpl(val context: Context, private val modelPath: String) : MediaPipeManager {
    private var handLandmarkerHelper: HandLandmarkerHelper? = null

    override fun detectImage(mpImageInput: MPImageInput) {
        if (handLandmarkerHelper?.isClose() == true) handLandmarkerHelper?.setupHandLandmarker()
        handLandmarkerHelper?.detectLiveStream(mpImageInput.image)
    }

    override fun setMPDetectionListener(
        mpDetectionListener: MPDetectionListener,
        settingsModel: SettingsModel
    ) {
        if (handLandmarkerHelper == null) handLandmarkerHelper = HandLandmarkerHelper(
            context = context,
            modelPath = modelPath,
            handLandmarkerHelperListener = mpDetectionListener
        )
        handLandmarkerHelper?.settingsModel = settingsModel
        handLandmarkerHelper?.handLandmarkerHelperListener = mpDetectionListener
        handLandmarkerHelper?.clearHandLandmarker()
        handLandmarkerHelper?.setupHandLandmarker()
    }
}
