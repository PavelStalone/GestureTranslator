import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
    alias(libs.plugins.com.ortin.gesturetranslator.android.ktor)
}

android {
    namespace = "com.ortin.gesturetranslator.domain"
}
