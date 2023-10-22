package com.ortin.gesturetranslator.core.managers.mediapipe.models

import android.net.Uri

data class MPVideoInput(val videoUri: Uri, val inferenceIntervalMs: Long)
