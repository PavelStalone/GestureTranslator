package com.ortin.gesturetranslator.presentation.screens.settings

val settingsScreens = listOf(
    SettingsScreens.Premium,
    SettingsScreens.AppSettings,
    SettingsScreens.Bugreport,
    SettingsScreens.About,
)

val settingsScreenRoutes = settingsScreens.map { i -> i.route }

sealed class SettingsScreens(
    val route: String,
    val title: String,
    val primary: Boolean
) {
    data object Premium : SettingsScreens(
        route = "settings_premium",
        title = "Войти в аккаунт",
        primary = true
    )

    data object AppSettings : SettingsScreens(
        route = "settings_appSettings",
        title = "Настройки приложения",
        primary = false
    )

    data object Bugreport : SettingsScreens(
        route = "settings_bugreport",
        title = "Сообщить об ошибке",
        primary = false
    )

    data object About : SettingsScreens(
        route = "settings_about",
        title = "О приложении",
        primary = false
    )
}
