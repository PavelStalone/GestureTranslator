package com.ortin.gesturetranslator.main.navigation

import androidx.compose.runtime.Immutable

/**
 * Sealed class representing the different authentication screens in the app
 *
 * @property route route associated with the screen
 */
@Immutable
sealed class MainApplicationScreenRoutes(val route: String) {

    /**
     * Main screen for gesture translation
     */
    data object MainScreenRoutes : MainApplicationScreenRoutes("MainScreen")

    /**
     * Gallery screen to translate a gesture from an image
     */
    data object GalleryScreenRoutes : MainApplicationScreenRoutes("GalleryScreen")

    /**
     * Settings screen to fine-tune the application
     */
    data object SettingsScreenRoutes : MainApplicationScreenRoutes("Settings")

    /**
     * Gesture list screen to view available gestures
     */
    data object GestureListScreenRoutes : MainApplicationScreenRoutes("GestureList")

    /**
     * Builds the route string with the provided arguments
     *
     * @param args Arguments to be appended to the route string
     * @return Constructed route string with the supplied arguments
     */
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
