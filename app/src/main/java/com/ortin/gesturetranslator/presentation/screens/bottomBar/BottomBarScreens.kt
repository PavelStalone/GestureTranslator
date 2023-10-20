package com.ortin.gesturetranslator.presentation.screens.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

val bottomBarScreens = listOf(
    BottomBarScreens.Home,
    BottomBarScreens.Gallery,
    BottomBarScreens.Gestures,
    BottomBarScreens.Settings
)

val bottomBarRoutes = bottomBarScreens.map { i -> i.route }

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreens(
        route = "camera",
        title = "Камера",
        icon = Icons.Default.Home
    )

    data object Gallery : BottomBarScreens(
        route = "gallery",
        title = "Галлерея",
        icon = Icons.Default.Home
    )

    data object Gestures : BottomBarScreens(
        route = "gestures",
        title = "Жесты",
        icon = Icons.Default.Home
    )

    data object Settings : BottomBarScreens(
        route = "settings",
        title = "Настройки",
        icon = Icons.Default.Home
    )
}
