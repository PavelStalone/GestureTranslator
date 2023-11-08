package com.ortin.gesturetranslator.presentation.screens.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewCozy
import androidx.compose.material.icons.outlined.BackHand
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Settings
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
    val icon: ImageVector,
    val icon_outlined: ImageVector
) {
    data object Home : BottomBarScreens(
        route = "camera",
        title = "Камера",
        icon = Icons.Default.CameraAlt,
        icon_outlined = Icons.Outlined.CameraAlt
    )

    data object Gallery : BottomBarScreens(
        route = "gallery",
        title = "Галлерея",
        icon = Icons.Default.Image,
        icon_outlined = Icons.Outlined.Image
    )

    data object Gestures : BottomBarScreens(
        route = "gestures",
        title = "Жесты",
        icon = Icons.Default.BackHand,
        icon_outlined = Icons.Outlined.BackHand
    )

    data object Settings : BottomBarScreens(
        route = "settings",
        title = "Настройки",
        icon = Icons.Default.Settings,
        icon_outlined = Icons.Outlined.Settings
    )
}
