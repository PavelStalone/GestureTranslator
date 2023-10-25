package com.ortin.gesturetranslator.domain.models

enum class CameraFacingSettings(val mode: Int) {
    LENS_FACING_BACK(1),
    LENS_FACING_FRONT(0),
    LENS_FACING_UNKNOWN(-1),
    LENS_FACING_EXTERNAL(2)
}
