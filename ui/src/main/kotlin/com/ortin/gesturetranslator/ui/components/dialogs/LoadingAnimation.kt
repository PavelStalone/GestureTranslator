package com.ortin.gesturetranslator.ui.components.dialogs

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleCount: Int = 3,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    duration: Int = 300,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 40.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val offsetDuration = duration / circleCount
    val circles = List(circleCount) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = duration * 4
                    0.0f at offsetDuration * index with LinearOutSlowInEasing
                    1.0f at duration * 1 + offsetDuration * index with LinearOutSlowInEasing
                    0.0f at duration * 2 + offsetDuration * index with LinearOutSlowInEasing
                    0.0f at duration * 4 with LinearOutSlowInEasing
                },
                repeatMode = RepeatMode.Restart
            ),
            label = "position circle"
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circles.forEach {
            Box(modifier = Modifier
                .size(circleSize)
                .graphicsLayer {
                    translationY = it.value * travelDistance.toPx() * -1
                }
                .background(
                    color = circleColor, shape = CircleShape
                )
            )
        }
    }
}

@Preview
@Composable
fun LoadingAnimationPreview() {
    GestureTranslatorTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoadingAnimation()
        }
    }
}
