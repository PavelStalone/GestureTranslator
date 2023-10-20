package com.ortin.gesturetranslator.presentation.screens.bottomBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.gesturetranslator.presentation.screens.MainScreen
import com.ortin.gesturetranslator.presentation.ui.theme.OrtinTheme

@Composable
fun GesturesScreen() {

}

@Preview
@Composable
fun GesturesScreenPreview() {
    OrtinTheme {
        MainScreen(BottomBarScreens.Gestures.route)
    }
}
