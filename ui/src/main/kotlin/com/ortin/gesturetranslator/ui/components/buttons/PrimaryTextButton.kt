package com.ortin.gesturetranslator.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun PrimaryTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null
) {
    val localDimensions = LocalDimensions.current

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        contentPadding = PaddingValues(
            vertical = localDimensions.verticalSmall,
            horizontal = localDimensions.horizontalPreLarge
        ),
        shape = RoundedCornerShape(size = localDimensions.buttonsCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        leftIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            text = text,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
        rightIcon?.let {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PrimaryTextButtonPreview() {
    GestureTranslatorTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PrimaryTextButton(
                modifier = Modifier,
                text = "Telegram",
                onClick = {}
            )
        }
    }
}
