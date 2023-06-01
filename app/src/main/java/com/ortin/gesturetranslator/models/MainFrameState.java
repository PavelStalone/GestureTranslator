package com.ortin.gesturetranslator.models;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainFrameState {

    private boolean flashlight = false;
    private boolean realtimeButton = true;
    private int bottomSheetBehavior = BottomSheetBehavior.STATE_COLLAPSED;

    public MainFrameState() {

    }

    public MainFrameState(boolean flashlight, boolean realtimeButton, int bottomSheetBehavior) {
        this.flashlight = flashlight;
        this.realtimeButton = realtimeButton;
        this.bottomSheetBehavior = bottomSheetBehavior;
    }

    public boolean isFlashlight() {
        return flashlight;
    }

    public boolean isRealtimeButton() {
        return realtimeButton;
    }

    public int getBottomSheetBehavior() {
        return bottomSheetBehavior;
    }
}
