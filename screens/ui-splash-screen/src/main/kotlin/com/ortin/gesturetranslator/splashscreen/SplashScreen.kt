package com.ortin.gesturetranslator.splashscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty

@Composable
fun SplashScreen(
    onAnimationEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.logotype)
    )
    val progress by animateLottieCompositionAsState(composition = composition)
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
        modifier = modifier.fillMaxSize(),
        composition = composition,
        dynamicProperties = dynamicProperties
    )

    if (progress == 1f) {
        onAnimationEnd()
    }
}
