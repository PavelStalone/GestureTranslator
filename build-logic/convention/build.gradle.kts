import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.ortin.gesturetranslator.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.ksp)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.ortin.gesturetranslator.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "com.ortin.gesturetranslator.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "com.ortin.gesturetranslator.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.ortin.gesturetranslator.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "com.ortin.gesturetranslator.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidRoom") {
            id = "com.ortin.gesturetranslator.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidCamera") {
            id = "com.ortin.gesturetranslator.android.camera"
            implementationClass = "AndroidCameraXConventionPlugin"
        }
    }
}
