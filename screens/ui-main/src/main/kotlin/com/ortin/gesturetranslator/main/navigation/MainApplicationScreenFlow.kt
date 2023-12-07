package com.ortin.gesturetranslator.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.main.screens.MainScreen
import com.ortin.gesturetranslator.main.viewmodel.MainTranslatorViewModel

@Composable
fun MainApplicationScreenFlow(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val mainScreenViewModel: MainTranslatorViewModel = hiltViewModel()
    // TODO: add view models

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainApplicationScreenRoutes.MainScreenRoutes.route
    ) {
        /**
         * Main screen for gesture translation
         */
        composable(
            route = MainApplicationScreenRoutes.MainScreenRoutes.route
        ) {
            MainScreen(viewModel = mainScreenViewModel)
        }

        /**
         * Gallery screen to translate a gesture from an image
         */
        composable(
            route = MainApplicationScreenRoutes.GalleryScreenRoutes.route
        ) {
            // TODO: add MainScreen
        }

        /**
         * Gesture list screen to view available gestures
         */
        composable(
            route = MainApplicationScreenRoutes.GestureListScreenRoutes.route
        ) {
            // TODO: add GestureListScreen
        }

        /**
         * Settings screen to fine-tune the application
         */
        composable(
            route = MainApplicationScreenRoutes.SettingsScreenRoutes.route
        ) {
            // TODO: add SettingsScreen
        }
    }
}
