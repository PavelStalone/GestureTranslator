import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.com.ortin.gesturetranslator.android.library)
    alias(libs.plugins.com.ortin.gesturetranslator.android.hilt)
    alias(libs.plugins.com.ortin.gesturetranslator.android.ktor)
}

android {
    namespace = "com.ortin.gesturetranslator.network"

    val localProperties = Properties()

    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProperties.load(FileInputStream(localPropertiesFile))
    }

    fun propertyOrEmpty(name: String): String {
        return System.getenv(name) ?: localProperties.getProperty(name) ?: ""
    }

    defaultConfig {
        buildConfigField(
            "String",
            "INFERENCE_TOKEN",
            "\"${propertyOrEmpty("INFERENCE_TOKEN")}\""
        )
    }
}

dependencies {
    /**
     * Modules implementations
     */
    implementation(project(":domain"))

    testImplementation(libs.kotlinx.coroutines.test)
}
