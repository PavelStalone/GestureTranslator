package com.ortin.gesturetranslator.presentation.screens.bottomBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.presentation.screens.MainScreen
import com.ortin.gesturetranslator.presentation.screens.settings.settingsScreens
import com.ortin.gesturetranslator.presentation.ui.theme.SurfaceText
import com.ortin.gesturetranslator.presentation.ui.theme.MainButton
import com.ortin.gesturetranslator.presentation.ui.theme.OrtinTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        SurfaceText(
            // Text is not added to strings.xml because of no text
            text = "Залогиньтесь пж",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        settingsScreens.forEachIndexed { _, item ->
            MainButton(
                text = item.title,
                isPrimary = item.primary,
                isCentered = false
            ) {
                // navController.navigate(item.route)
            }
            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }
    }
}

@Preview
@Composable
fun ViewSettingsScreen() {
    OrtinTheme {
        Surface {
            MainScreen(BottomBarScreens.Settings.route)
        }
    }
}
