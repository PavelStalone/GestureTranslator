package com.ortin.gesturetranslator.splashscreen

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun SplashScreen(
    @RawRes animationJson: Int,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = animationJson)
    )

    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                MaterialTheme.colorScheme.onBackground.hashCode(),
                BlendModeCompat.SRC_ATOP
            ),
            keyPath = arrayOf(
                "**"
            )
        )
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        dynamicProperties = dynamicProperties
    )
}

@Preview
@Composable
fun LottieSplashScreenPreview() {
    GestureTranslatorTheme(darkTheme = true) {
        Surface {
            SplashScreen(
                animationJson = R.raw.logotype,
                Modifier.fillMaxSize()
            )
        }
    }
}
