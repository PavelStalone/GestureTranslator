package com.ortin.gesturetranslator.main.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ortin.gesturetranslator.main.screens.GestureListScreen
import com.ortin.gesturetranslator.main.screens.ImageFromGalleryScreen
import com.ortin.gesturetranslator.main.screens.MainScreen
import com.ortin.gesturetranslator.main.screens.SettingsScreen
import com.ortin.gesturetranslator.main.viewmodel.GalleryViewModel
import com.ortin.gesturetranslator.main.viewmodel.MainTranslatorViewModel
import com.ortin.gesturetranslator.main.viewmodel.SettingsScreenViewModel

fun NavGraphBuilder.mainApplicationScreenFlow(
    mainScreenViewModel: MainTranslatorViewModel,
    galleryViewModel: GalleryViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
    route: String,
    action: () -> Unit = {}
) {
    navigation(
        startDestination = MainApplicationScreenRoutes.MainScreenRoutes.route,
        route = route
    ) {
        /**
         * Main screen for gesture translation
         */
        composable(
            route = MainApplicationScreenRoutes.MainScreenRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            action()
            MainScreen(viewModel = mainScreenViewModel)
        }

        /**
         * Gallery screen to translate a gesture from an image
         */
        composable(
            route = MainApplicationScreenRoutes.GalleryScreenRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            ImageFromGalleryScreen(viewModel = galleryViewModel)
        }

        /**
         * Gesture list screen to view available gestures
         */
        composable(
            route = MainApplicationScreenRoutes.GestureListScreenRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            GestureListScreen()
        }

        /**
         * Settings screen to fine-tune the application
         */
        composable(
            route = MainApplicationScreenRoutes.SettingsScreenRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            SettingsScreen(settingsScreenViewModel = settingsScreenViewModel)
        }
    }
}
