import com.ortin.gesturetranslator.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidCameraXConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("androidx-camera-core").get())
                add("implementation", libs.findLibrary("androidx-camera-camera2").get())
                add("implementation", libs.findLibrary("androidx-camera-lifecycle").get())
                add("implementation", libs.findLibrary("androidx-camera-video").get())
                add("implementation", libs.findLibrary("androidx-camera-view").get())
                add("implementation", libs.findLibrary("androidx-camera-extensions").get())
            }
        }
    }
}