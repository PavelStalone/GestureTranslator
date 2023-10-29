import com.android.build.gradle.LibraryExtension
import com.ortin.gesturetranslator.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureAndroidCompose(extensions.getByType<LibraryExtension>())
        }
    }
}
