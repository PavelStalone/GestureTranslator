pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GestureTranslator"
include(":app")
include(":core")
include(":domain")
include(":feature")
include(":data")
include(":ui")
include(":network")
include(":screens:ui-auth")
include(":screens:ui-main")
include(":common")
include(":screens:ui-splash-screen")
