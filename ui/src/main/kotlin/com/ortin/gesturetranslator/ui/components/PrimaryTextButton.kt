package com.ortin.gesturetranslator.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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

@Composable
fun PrimaryTextButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null
) {
    Button(
        onClick = {
            onButtonClick()
        },
        modifier = modifier,
        enabled = isEnabled,
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 16.dp
        ),
        shape = RoundedCornerShape(size = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row {
            leftIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null)
            }
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            rightIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun PrimaryTextButtonPreview(){
    PrimaryTextButton(
        text = "Войти",
        onButtonClick = {}
    )
}
