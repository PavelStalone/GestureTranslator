package com.ortin.gesturetranslator.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun PrimaryTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    @DrawableRes leftIconId: Int? = null,
    @DrawableRes rightIconId: Int? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null
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
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        val spacerModifier = Modifier.weight(0.5f)

        leftIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = null
            )
            Spacer(modifier = spacerModifier)
        }
        leftIconId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null
            )
            Spacer(modifier = spacerModifier)
        }
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        rightIcon?.let {
            Spacer(modifier = spacerModifier)
            Icon(
                imageVector = it,
                contentDescription = null
            )
        }
        rightIconId?.let {
            Spacer(modifier = spacerModifier)
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
        Surface(modifier = Modifier.fillMaxWidth()) {
            PrimaryTextButton(
                text = "Войти",
                onClick = {}
            )
        }
    }
}
