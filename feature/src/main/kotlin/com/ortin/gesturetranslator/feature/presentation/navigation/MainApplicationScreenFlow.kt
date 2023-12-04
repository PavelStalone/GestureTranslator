package com.ortin.gesturetranslator.feature.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApplicationScreenFlow(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    // TODO: add view models

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainApplicationScreen.MainScreen.route
    ) {

        /**
         * Main screen for gesture translation
         */
        composable(
            route = MainApplicationScreen.MainScreen.route
        ) {
            //TODO: add MainScreen
        }

        /**
         * App info screen to view all developers and major information about app
         */
        composable(
            route = MainApplicationScreen.AppInfoScreen.route
        ) {
            // TODO: add AppInfoScreen
        }

        /**
         * Settings screen to fine-tune the application
         */
        composable(
            route = MainApplicationScreen.SettingsScreen.route
        ) {
            // TODO: add SettingsScreen
        }

        /**
         * Gesture list screen to view available gestures
         */
        composable(
            route = MainApplicationScreen.GestureListScreen.route
        ) {
            // TODO: add GestureListScreen
        }

        /**
         * Main menu screen for additional information about app
         */
        composable(
            route = MainApplicationScreen.MainMenuScreen.route
        ) {
            // TODO: add MainMenuScreen
        }
    }
}
