package com.ortin.gesturetranslator.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BackHand
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon_filled: ImageVector,
    val icon_outlined: ImageVector
) {
    data object Home : BottomBarScreens(
        route = "camera",
        title = "Камера",
        icon_filled = Icons.Default.CameraAlt,
        icon_outlined = Icons.Outlined.CameraAlt
    )

    data object Gallery : BottomBarScreens(
        route = "gallery",
        title = "Галлерея",
        icon_filled = Icons.Default.Image,
        icon_outlined = Icons.Outlined.Image
    )

    data object Gestures : BottomBarScreens(
        route = "gestures",
        title = "Жесты",
        icon_filled = Icons.Default.BackHand,
        icon_outlined = Icons.Outlined.BackHand
    )

    data object Settings : BottomBarScreens(
        route = "settings",
        title = "Настройки",
        icon_filled = Icons.Default.Settings,
        icon_outlined = Icons.Outlined.Settings
    )
}
