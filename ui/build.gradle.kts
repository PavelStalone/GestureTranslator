plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.library.compose)
}

android {
    namespace = "com.ortin.gesturetranslator.ui"
}

dependencies {
    /**
     * Core implementations
     */
    implementation(libs.androidx.core.ktx)

    /**
     * Modules implementations
     */
    implementation(project(":domain"))

    /**
     * Navigation dependencies
     */
    implementation(libs.androidx.navigation.compose)
}
