package com.ortin.gesturetranslator.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController, currRoute: String) {
    NavHost(
        navController = navController,
        startDestination = currRoute
    ) {
        composable(route = BottomBarScreens.Home.route) {
        }

        composable(route = BottomBarScreens.Gallery.route) {
        }

        composable(route = BottomBarScreens.Gestures.route) {
        }

        composable(route = BottomBarScreens.Settings.route) {
            SettingsScreen()
        }

        composable(route = SettingsScreens.AppSettings.route) {
        }

        composable(route = SettingsScreens.Premium.route) {
        }

        composable(route = SettingsScreens.Bugreport.route) {
        }

        composable(route = SettingsScreens.About.route) {
        }
    }
}
