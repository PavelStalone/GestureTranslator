package com.ortin.gesturetranslator.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
internal fun AnimatedText(
    text: String,
) {
    var count by remember { mutableStateOf(text) }
    val duration = 400

    SideEffect {
        count = text
    }

    AnimatedContent(
        targetState = count,
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = duration)
            ) togetherWith ExitTransition.None
        },
        label = ""
    ) { item ->
        Text(
            text = item,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 32.sp
            )
        )
    }
}