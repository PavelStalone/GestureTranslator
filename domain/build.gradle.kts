import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
}

android {
    namespace = "com.ortin.gesturetranslator.domain"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
