package com.ortin.gesturetranslator.app.navigation

import androidx.compose.runtime.Immutable

/**
 * Sealed class representing the different authentication screens in the app
 *
 * @property route route associated with the screen
 */
@Immutable
sealed class ApplicationScreenRoutes(val route: String) {

    data object SplashScreenRoutes : ApplicationScreenRoutes("SplashScreenRoute")
    data object AuthScreenFlowRoutes : ApplicationScreenRoutes("AuthScreenFlow")
    data object MainScreenFlowRoutes : ApplicationScreenRoutes("MainScreenFlow")

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
