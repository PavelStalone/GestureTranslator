plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.library.compose)
}

android {
    namespace = "com.ortin.gesturetranslator.auth"
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
    implementation(project(":ui"))

    /**
     * Navigation dependencies
     */
    implementation(libs.androidx.navigation.compose)
}
