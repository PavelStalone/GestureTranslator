plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
    alias(libs.plugins.com.ortin.gesturetranslator.android.camera)
}

android {
    namespace = "com.ortin.gesturetranslator.feature"
}

dependencies {
    /**
     * Modules implementations
     */
    implementation(project(":domain"))

    implementation("com.google.guava:guava:30.1-jre")
}
