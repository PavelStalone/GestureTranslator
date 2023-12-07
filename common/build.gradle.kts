plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
}

android {
    namespace = "com.ortin.gesturetranslator.common"
}

dependencies {
    /**
     * Core implementations
     */
    implementation(libs.androidx.core.ktx)

    /**
     * Android implementations
     */
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}
