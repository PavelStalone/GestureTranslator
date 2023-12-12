package com.ortin.gesturetranslator.app.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.main.navigation.mainApplicationScreenFlow
import com.ortin.gesturetranslator.main.viewmodel.GalleryViewModel
import com.ortin.gesturetranslator.main.viewmodel.MainTranslatorViewModel
import com.ortin.gesturetranslator.main.viewmodel.SettingsScreenViewModel
import com.ortin.gesturetranslator.splashscreen.SplashScreen

@Composable
fun ApplicationScreenFlow(
    onBottomBarVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val mainScreenViewModel: MainTranslatorViewModel = hiltViewModel()
    val settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel()
    val galleryViewModel: GalleryViewModel = hiltViewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ApplicationScreenRoutes.SplashScreenRoutes.route
    ) {
        composable(
            route = ApplicationScreenRoutes.SplashScreenRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            onBottomBarVisibilityChange(false)
            SplashScreen(onAnimationEnd = { navController.navigate(ApplicationScreenRoutes.MainScreenFlowRoutes.route) })
        }

        composable(
            route = ApplicationScreenRoutes.AuthScreenFlowRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            // Todo AuthScreenFlow
            onBottomBarVisibilityChange(false)
        }

        mainApplicationScreenFlow(
            mainScreenViewModel = mainScreenViewModel,
            galleryViewModel = galleryViewModel,
            settingsScreenViewModel = settingsScreenViewModel,
            route = ApplicationScreenRoutes.MainScreenFlowRoutes.route
        ) {
            onBottomBarVisibilityChange(true)
        }
    }
}
