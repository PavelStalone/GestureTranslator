package com.ortin.gesturetranslator.app.presentation

import androidx.compose.runtime.Immutable

/**
 * Sealed class representing the different screens in the Digital Office app
 *
 * @property route Route associated with the screen
 */
@Immutable
sealed class GestureTranslatorScreen(val route: String) {

    /**
     * Screens for user authentication
     */
    data object AuthScreenFlow : GestureTranslatorScreen("Auth")

    /**
     * Screens for
     */
    data object MainApplicationScreenFlow : GestureTranslatorScreen("TimeTracking")

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
