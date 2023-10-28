package com.ortin.gesturetranslator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.PasswordTextField
import com.ortin.gesturetranslator.ui.components.PrimaryTextButton
import com.ortin.gesturetranslator.ui.components.PrimaryTextField
import com.ortin.gesturetranslator.ui.components.SecondaryTextButton
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun LoginScreen(
    isLoginButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_ortin_logo),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.login_support_screen),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(48.dp))
        PrimaryTextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            onTextChange = {},
            placeholder = stringResource(id = R.string.enter_login)
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            modifier = Modifier.padding(horizontal = 8.dp),
            onTextChange = {},
            description = stringResource(id = R.string.enter_password)
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryTextButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.login_button_text),
            onButtonClick = {},
            isEnabled = isLoginButtonEnabled
        )
        Spacer(modifier = Modifier.height(16.dp))
        SecondaryTextButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.without_login_button_text),
            onButtonClick = {},
            isEnabled = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.without_accaunt),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.sign_up),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp
                ),
                modifier = Modifier.clickable(
                    onClick = {/* TODO: Handle click */ }
                )
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.reset_password),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable(
                    onClick = {/* TODO: Handle click */ }
                )
        )
    }
}

@Preview
@Composable
fun LoginScreenPreviewLight() {
    GestureTranslatorTheme {
        LoginScreen(
            isLoginButtonEnabled = true,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Preview
@Composable
fun LoginScreenPreviewDark() {
    GestureTranslatorTheme(darkTheme = true) {
        LoginScreen(
            isLoginButtonEnabled = true,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
