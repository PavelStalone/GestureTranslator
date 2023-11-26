package com.ortin.gesturetranslator.ui

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "240 dpi (Nexus One)",
    device = "id:Nexus One",
    group = "density test"
)
@Preview(
    name = "320 dpi (Pixel 3)",
    device = "id:pixel_3",
    group = "density test"
)
@Preview(
    name = "420 dpi (Pixel 6)",
    device = "id:pixel_6",
    group = "density test"
)
annotation class AdaptiveTestPreviews

@Preview(
    name = "small font",
    fontScale = 0.8f,
    group = "font scales"
)
@Preview(
    name = "standard font",
    fontScale = 1f,
    group = "font scales"
)
@Preview(
    name = "large font",
    fontScale = 1.5f,
    group = "font scales"
)
annotation class FontScalePreviews
