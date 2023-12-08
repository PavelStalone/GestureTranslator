package com.ortin.gesturetranslator.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun RadioButton(
    text: String,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    checked: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null
) {
    val localDimensions = LocalDimensions.current

    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clip(RoundedCornerShape(size = localDimensions.buttonsCornerRadius))
            .background(color = containerColors(isEnabled, checked).value)
            .toggleable(
                value = checked,
                onValueChange = onClick,
                enabled = isEnabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = localDimensions.buttonsCornerRadius
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor(
                enabled = isEnabled,
                checked = checked
            ).value
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = localDimensions.horizontalMedium,
                        vertical = localDimensions.verticalSmall
                    )
            ) {
                leftIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.weight(0.5f))
                }
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
                rightIcon?.let {
                    Spacer(modifier = Modifier.weight(0.5f))
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun containerColors(enabled: Boolean, checked: Boolean): State<Color> {
    val target = when {
        !enabled -> MaterialTheme.colorScheme.surface
        !checked -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.primary
    }
    return rememberUpdatedState(target)
}

@Composable
fun contentColor(enabled: Boolean, checked: Boolean): State<Color> {
    val target = when {
        !enabled -> MaterialTheme.colorScheme.onSurface
        !checked -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onPrimary
    }
    return rememberUpdatedState(target)
}

@Preview
@Composable
fun RadioButtonPreview() {
    GestureTranslatorTheme {
        Surface(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        ) {
            RadioButton(
                text = "Telegram",
                onClick = {}
            )
        }
    }
}
