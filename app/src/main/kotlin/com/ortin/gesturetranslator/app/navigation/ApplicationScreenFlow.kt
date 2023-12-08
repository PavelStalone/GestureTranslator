package com.ortin.gesturetranslator.app.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.main.navigation.MainApplicationScreenFlow
import com.ortin.gesturetranslator.splashscreen.SplashScreen

@Composable
fun ApplicationScreenFlow(
    onBottomBarVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    mainNavController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ApplicationScreenRoutes.MainScreenFlowRoutes.route
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

        composable(
            route = ApplicationScreenRoutes.MainScreenFlowRoutes.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            onBottomBarVisibilityChange(true)
            MainApplicationScreenFlow(navController = mainNavController)
        }
    }
}
