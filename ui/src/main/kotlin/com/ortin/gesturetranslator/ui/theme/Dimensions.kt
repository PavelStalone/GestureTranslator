package com.ortin.gesturetranslator.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val fieldsSpacer: Dp = 4.dp,
    val horizontalXLarge: Dp = 56.dp,
    val horizontalLarge: Dp = 32.dp,
    val horizontalMedium: Dp = 16.dp,
    val horizontalSmall: Dp = 12.dp,
    val verticalTiny: Dp = 8.dp,
    val verticalSmall: Dp = 12.dp,
    val verticalMedium: Dp = 24.dp,
    val verticalLarge: Dp = 32.dp,
    val verticalXlarge: Dp = 48.dp,
    val verticalPreExtraLarge: Dp = 80.dp,
    val textFieldsCornerRadius: Dp = 12.dp,
    val buttonsCornerRadius: Dp = 100.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }