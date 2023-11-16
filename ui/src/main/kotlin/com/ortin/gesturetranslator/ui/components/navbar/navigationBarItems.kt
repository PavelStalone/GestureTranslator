package com.ortin.gesturetranslator.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

val navigationBarItems = listOf(
    NavigationBarItems.Home,
    NavigationBarItems.Gallery,
    NavigationBarItems.Gestures,
    NavigationBarItems.Settings
)

sealed class NavigationBarItems(
    val route: String,
    val title: String,
    val iconFilled: Int,
    val iconOutlined: Int
) {
    data object Home : NavigationBarItems(
        route = "camera",
        title = "Камера",
        iconFilled = R.drawable.photo_camera_filled,
        iconOutlined = R.drawable.photo_camera
    )

    data object Gallery : NavigationBarItems(
        route = "gallery",
        title = "Галлерея",
        iconFilled = R.drawable.image_filled,
        iconOutlined = R.drawable.image
    )

    data object Gestures : NavigationBarItems(
        route = "gestures",
        title = "Жесты",
        iconFilled = R.drawable.grid_view_filled,
        iconOutlined = R.drawable.grid_view
    )

    data object Settings : NavigationBarItems(
        route = "settings",
        title = "Настройки",
        iconFilled = R.drawable.settings_filled,
        iconOutlined = R.drawable.settings
    )
}
