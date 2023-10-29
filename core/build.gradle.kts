plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
}

android {
    namespace = "com.ortin.gesturetranslator.core"
}

dependencies {
    /**
     * MediaPipe implementations
     */
    implementation(libs.com.google.mediapipe)

    /**
     * Modules implementations
     */
    implementation(project(":domain"))
}
