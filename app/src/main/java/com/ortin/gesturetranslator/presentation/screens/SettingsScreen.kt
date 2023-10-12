package com.ortin.gesturetranslator.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.presentation.ui.theme.SurfaceText
import com.ortin.gesturetranslator.presentation.ui.theme.ThemedButton
import com.ortin.gesturetranslator.presentation.ui.theme.OrtinTheme
import com.ortin.gesturetranslator.presentation.ui.theme.loginPlease

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.padding(20.dp, 0.dp)
    ) {
        SurfaceText(text = loginPlease)
        Spacer(modifier = Modifier.height(20.dp))

        settingsScreens.forEachIndexed { index, item ->
            ThemedButton(text = item.title, isPrimary = item.primary, isCentered = false) {
//                navController.navigate(item.route)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun ViewSettingsScreen() {
    OrtinTheme {
        MainScreen(BottomBarScreens.Settings.route, 3)
    }
}
