package com.ortin.gesturetranslator.core.managers.mediapipe.models

import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult

data class MPDetection(val result: HandLandmarkerResult, val mpImage: MPImage)
