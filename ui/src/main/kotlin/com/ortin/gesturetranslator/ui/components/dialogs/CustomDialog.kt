package com.ortin.gesturetranslator.ui.components.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val dimensions = LocalDimensions.current
    val shadowSize = 8.dp
    val dialogWidth = 300.dp

    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                var animateIn by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) { animateIn = true }

                AnimatedVisibility(
                    visible = animateIn,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .pointerInput(Unit) { detectTapGestures { onDismissRequest() } }
                            .background(Color.Black.copy(alpha = .56f))
                            .fillMaxSize()
                    )
                }

                AnimatedVisibility(
                    visible = animateIn,
                    enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)) + scaleIn(
                        initialScale = .8f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                    exit = slideOutVertically { it / 8 } + fadeOut() + scaleOut(targetScale = .95f)
                ) {
                    Box(
                        Modifier
                            .pointerInput(Unit) { detectTapGestures { } }
                            .shadow(shadowSize, shape = RoundedCornerShape(dimensions.horizontalMedium))
                            .width(dialogWidth)
                            .clip(RoundedCornerShape(dimensions.horizontalMedium))
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomDialogPreview() {
    var showDialog by remember { mutableStateOf(false) }

    CustomDialog(
        showDialog = showDialog,
        onDismissRequest = { showDialog = false }
    ) {
        CustomDialogContent(
            title = "Oops, something went wrong!",
            description = "Your phone will be destroy in 5 seconds. Sorry :)",
            cancelButtonText = "Cancel",
            onConfirmButtonClick = { showDialog = false },
            onDismissRequest = { showDialog = false }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                showDialog = true
            }
        ) {
            Text(text = "Dialog")
        }
    }
}
