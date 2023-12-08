package com.ortin.gesturetranslator.main.components.navbar

import com.ortin.gesturetranslator.main.navigation.MainApplicationScreenRoutes
import com.ortin.gesturetranslator.ui.R

val navigationBarItems = listOf(
    NavigationBarItem.Home,
    NavigationBarItem.Gallery,
    NavigationBarItem.Gestures,
    NavigationBarItem.Settings
)

sealed class NavigationBarItem(
    val route: String,
    val title: String,
    val iconFilled: Int,
    val iconOutlined: Int
) {
    data object Home : NavigationBarItem(
        route = MainApplicationScreenRoutes.MainScreenRoutes.route,
        title = "Главная",
        iconFilled = R.drawable.photo_camera_filled,
        iconOutlined = R.drawable.photo_camera
    )

    data object Gallery : NavigationBarItem(
        route = MainApplicationScreenRoutes.GalleryScreenRoutes.route,
        title = "Галерея",
        iconFilled = R.drawable.image_filled,
        iconOutlined = R.drawable.image
    )

    data object Gestures : NavigationBarItem(
        route = MainApplicationScreenRoutes.GestureListScreenRoutes.route,
        title = "Жесты",
        iconFilled = R.drawable.grid_view_filled,
        iconOutlined = R.drawable.grid_view
    )

    data object Settings : NavigationBarItem(
        route = MainApplicationScreenRoutes.SettingsScreenRoutes.route,
        title = "Настройки",
        iconFilled = R.drawable.settings_filled,
        iconOutlined = R.drawable.settings
    )
}
