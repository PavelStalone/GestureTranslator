package com.ortin.gesturetranslator.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun SecondaryTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    @DrawableRes leftIconId: Int? = null,
    @DrawableRes rightIconId: Int? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 16.dp
        ),
        shape = RoundedCornerShape(size = 100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant

        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            leftIconId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            rightIcon?.let {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }
            rightIconId?.let {
                Spacer(modifier = Modifier.weight(1f))
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
fun SecondaryTextButtonPreview() {
    GestureTranslatorTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            SecondaryTextButton(
                text = "Войти",
                onClick = {}
            )
        }
    }
}
