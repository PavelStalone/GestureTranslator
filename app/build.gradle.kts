import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.Download

plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.application)
    alias(libs.plugins.com.ortin.gesturetranslator.android.application.compose)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
    alias(libs.plugins.de.undercouch.download)

    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "com.ortin.gesturetranslator"

    defaultConfig {
        applicationId = "com.ortin.gesturetranslator"
        versionCode = 1
        versionName = "1.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

tasks.create<Download>("downloadModel"){
    src("https://storage.googleapis.com/mediapipe-models/hand_landmarker/hand_landmarker/float16/1/hand_landmarker.task")
    dest("$projectDir/src/main/assets/hand_landmarker.task")
    overwrite(false)
}
tasks.preBuild.dependsOn("downloadModel")

dependencies {
    /**
     * Lottie animation implementations
     */
    implementation("com.airbnb.android:lottie:3.4.0")

    /**
     * Async implementations
     */
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    implementation("androidx.navigation:navigation-fragment:2.7.3")
    implementation("androidx.navigation:navigation-ui:2.7.3")

    /**
     * Core dependencies
     */
    implementation(libs.androidx.core.ktx)

    /**
     * Android implementations
     */
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    /**
     *  Module dependencies
     */
    implementation(project(":domain"))
    implementation(project(":feature"))
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":ui"))

    /**
     * Github implementations
     */
    implementation("com.github.bumptech.glide:glide:4.12.0")
    ksp("com.github.bumptech.glide:compiler:4.12.0")
}
