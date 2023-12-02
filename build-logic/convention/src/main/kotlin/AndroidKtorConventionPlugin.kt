import com.ortin.gesturetranslator.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKtorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                add("implementation", libs.findLibrary("ktor-client-core").get())
                add("implementation", libs.findLibrary("ktor-client-cio").get())
                add("implementation", libs.findLibrary("ktor-client-okhttp").get())
                add("implementation", libs.findLibrary("ktor-client-android").get())
                add("implementation", libs.findLibrary("ktor-client-logging").get())
                add("implementation", libs.findLibrary("ktor-client-serialization").get())
                add("implementation", libs.findLibrary("ktor-client-content-negotiation").get())
                add("implementation", libs.findLibrary("ktor-serialization-kotlinx-json").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}
