package com.ortin.gesturetranslator.feature.presentation.navigation

import javax.annotation.concurrent.Immutable

/**
 * Sealed class representing the different authentication screens in the app
 *
 * @property route Route associated with the screen
 */
@Immutable
sealed class MainApplicationScreen(val route: String) {

    /**
     * Main screen for gesture translation
     */
    data object MainScreen : MainApplicationScreen("MainScreen")

    /**
     * Settings screen to fine-tune the application
     */
    data object SettingsScreen : MainApplicationScreen("Settings")

    /**
     * App info screen to view all developers and major information about app
     */
    data object AppInfoScreen : MainApplicationScreen("AppInfo")

    /**
     *  Main menu screen to view additional information about app
     */
    data object MenuScreen : MainApplicationScreen("MainMenu")

    /**
     * Gesture list screen to view available gestures
     */
    data object GestureListScreen : MainApplicationScreen("GestureList")

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
