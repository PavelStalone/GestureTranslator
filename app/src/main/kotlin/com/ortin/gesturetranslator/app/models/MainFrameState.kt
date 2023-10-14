package com.ortin.gesturetranslator.app.models

import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFrameState {
    var isFlashlight = false
        private set
    var isRealtimeButton = true
        private set
    var bottomSheetBehavior = BottomSheetBehavior.STATE_COLLAPSED
        private set

    constructor()
    constructor(flashlight: Boolean, realtimeButton: Boolean, bottomSheetBehavior: Int) {
        isFlashlight = flashlight
        isRealtimeButton = realtimeButton
        this.bottomSheetBehavior = bottomSheetBehavior
    }
}
