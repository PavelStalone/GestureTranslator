package com.ortin.gesturetranslator.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ortin.gesturetranslator.presentation.screens.bottomBar.BottomBarScreens
import com.ortin.gesturetranslator.presentation.screens.bottomBar.CameraScreen
import com.ortin.gesturetranslator.presentation.screens.bottomBar.GesturesScreen
import com.ortin.gesturetranslator.presentation.screens.bottomBar.SettingsScreen
import com.ortin.gesturetranslator.presentation.screens.bottomBar.SinglePhotoScreen
import com.ortin.gesturetranslator.presentation.screens.settings.SettingsScreens

@Composable
fun NavGraph(navController: NavHostController, currRoute: String) {
    NavHost(
        navController = navController,
        startDestination = currRoute
    ) {
        composable(route = BottomBarScreens.Home.route) {
            CameraScreen()
        }

        composable(route = BottomBarScreens.Gallery.route) {
            SinglePhotoScreen()
        }

        composable(route = BottomBarScreens.Gestures.route) {
            GesturesScreen()
        }

        composable(route = BottomBarScreens.Settings.route) {
            SettingsScreen(modifier = Modifier.background(MaterialTheme.colorScheme.background))
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
