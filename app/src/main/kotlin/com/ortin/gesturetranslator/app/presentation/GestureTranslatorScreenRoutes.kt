package com.ortin.gesturetranslator.app.presentation

import androidx.compose.runtime.Immutable

/**
 * Sealed class representing the different screens in the Digital Office app
 *
 * @property route route associated with the screen
 */
@Immutable
sealed class GestureTranslatorScreenRoutes(val route: String) {

    /**
     * Screens for user authentication
     */
    data object AuthScreenFlow : GestureTranslatorScreenRoutes("Auth")

    /**
     * Screens for viewing the main features of the application
     */
    data object MainApplicationScreenFlow : GestureTranslatorScreenRoutes("MainApplicationScreen")

    /**
     * Builds the route string with the provided arguments
     *
     * @param args arguments to be appended to the route string
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
