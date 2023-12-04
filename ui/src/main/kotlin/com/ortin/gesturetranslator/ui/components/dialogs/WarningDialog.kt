package com.ortin.gesturetranslator.ui.components.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun WarningDialog(
    title: String = "What",
    description: String? = null,
    cancelButtonText: String? = null,
    confirmButtonText: String = "Confirm",
    onConfirmButtonClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val dimensions = LocalDimensions.current
    val brushHeight = 150.dp
    val dividerWidth = 2.dp

    Column(Modifier.background(MaterialTheme.colorScheme.surface)) {
        var contentVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) { contentVisible = true }

        AnimatedVisibility(
            visible = contentVisible,
            enter = expandVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                expandFrom = Alignment.CenterVertically
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(brushHeight)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xE903A3A8),
                                Color(0xFF0221B6)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier.padding(dimensions.horizontalMedium),
            verticalArrangement = Arrangement.spacedBy(dimensions.verticalTiny)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            cancelButtonText?.let {
                Box(
                    modifier = Modifier
                        .padding(dimensions.verticalTiny)
                        .clip(RoundedCornerShape(dimensions.verticalTiny))
                        .weight(1f)
                        .clickable { onDismissRequest() }
                        .padding(vertical = dimensions.verticalStandardPlus),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(vertical = dimensions.verticalSmall)
                        .width(dividerWidth)
                        .fillMaxHeight()
                        .background(
                            MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                            shape = RoundedCornerShape(dimensions.verticalSmall)
                        )
                )
            }

            Box(
                modifier = Modifier
                    .padding(dimensions.horizontalTiny)
                    .clip(RoundedCornerShape(dimensions.horizontalTiny))
                    .weight(1f)
                    .clickable { onConfirmButtonClick() }
                    .padding(vertical = dimensions.verticalStandardPlus),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = confirmButtonText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0221B6)
                )
            }
        }
    }
}
