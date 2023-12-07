import com.ortin.gesturetranslator.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
                add("implementation", libs.findLibrary("androidx-hilt-ext-common").get())
                add("implementation", libs.findLibrary("google-dagger-hilt-android").get())
                add("ksp", libs.findLibrary("google-dagger-hilt-android-compiler").get())
                add("ksp", libs.findLibrary("androidx-hilt-ext-compiler").get())
            }
        }
    }
}
