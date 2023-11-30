buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.2")
    }
}

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    alias(libs.plugins.google.dagger.hilt) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
    alias(libs.plugins.org.gradle.android.cache.fix) apply false
    alias(libs.plugins.com.google.ksp) apply false
    alias(libs.plugins.de.undercouch.download) apply false
}
