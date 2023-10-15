package com.ortin.gesturetranslator.app.models

import com.google.android.material.bottomsheet.BottomSheetBehavior

data class MainFrameState(
    val flashLight: Boolean = false,
    val realTimeButton: Boolean = true,
    val bottomSheetBehavior: Int = BottomSheetBehavior.STATE_COLLAPSED
)
