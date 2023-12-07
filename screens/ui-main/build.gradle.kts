plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.library.compose)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
}

android {
    namespace = "com.ortin.gesturetranslator.main"
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
    implementation(project(":common"))

    /**
     * Navigation dependencies
     */
    implementation(libs.androidx.navigation.compose)
}
