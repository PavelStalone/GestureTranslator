plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
    alias(libs.plugins.com.ortin.gesturetranslator.android.room)
}

android {
    namespace = "com.ortin.data"
}

dependencies {
    /**
     * Modules implementations
     */
    implementation(project(":domain"))
}
