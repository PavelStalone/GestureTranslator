package com.ortin.gesturetranslator.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun PasswordTextField(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null
) {
    var passwordVisibilityState by rememberSaveable {
        mutableStateOf(true)
    }

    PrimaryTextField(
        onTextChange = onTextChange,
        modifier = modifier,
        trailingIcon = {
            if (passwordVisibilityState) {
                IconButton(onClick = { passwordVisibilityState = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_visibility_on),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = null
                    )
                }
            } else {
                IconButton(onClick = { passwordVisibilityState = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_visibility_off),
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = null
                    )
                }
            }
        },
        placeholder = placeholder,
        visualTransformation = if (passwordVisibilityState) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardType = KeyboardType.Password
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    GestureTranslatorTheme {
        Surface(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(LocalDimensions.current.verticalMedium)
        ) {
            PasswordTextField(onTextChange = {})
        }
    }
}
