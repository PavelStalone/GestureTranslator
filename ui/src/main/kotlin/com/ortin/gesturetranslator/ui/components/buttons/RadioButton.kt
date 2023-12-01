package com.ortin.gesturetranslator.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun RadioButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null
) {
    val localDimensions = LocalDimensions.current
    var isClicked by remember { mutableStateOf(false) }
    val borderStroke = 2.dp

    Button(
        onClick = {
            isClicked = !isClicked
            onClick()
        },
        modifier = modifier,
        enabled = isEnabled,
        border = BorderStroke(
            width = borderStroke,
            color = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(vertical = localDimensions.verticalSmall),
        shape = RoundedCornerShape(size = localDimensions.buttonsCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isClicked) {
                MaterialTheme.colorScheme.primary
            } else MaterialTheme.colorScheme.secondaryContainer,
            contentColor = if (isClicked) {
                MaterialTheme.colorScheme.onPrimary
            } else MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row {
            leftIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
            rightIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
        }
    }
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
