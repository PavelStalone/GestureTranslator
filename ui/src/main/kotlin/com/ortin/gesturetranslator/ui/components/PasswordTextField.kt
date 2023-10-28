package com.ortin.gesturetranslator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R

@Composable
fun PasswordTextField(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    isPasswordVisible: Boolean = true
) {
    var passwordVisibilityState by rememberSaveable {
        mutableStateOf(isPasswordVisible)
    }
    Box(modifier = modifier) {
        PrimaryTextField(
            onTextChange = onTextChange,
            modifier = modifier,
            trailingIcon =
            {
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
            placeholder = description,
            visualTransformation = if (passwordVisibilityState) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
    }
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        PasswordTextField(onTextChange = {})
    }
}
