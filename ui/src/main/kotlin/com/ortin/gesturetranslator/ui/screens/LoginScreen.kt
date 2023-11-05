package com.ortin.gesturetranslator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.PasswordTextField
import com.ortin.gesturetranslator.ui.components.PrimaryTextButton
import com.ortin.gesturetranslator.ui.components.PrimaryTextField
import com.ortin.gesturetranslator.ui.components.SecondaryTextButton
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun LoginScreen(
    isLoginButtonEnabled: Boolean, /* TODO: Replace with viewModel */
    modifier: Modifier = Modifier
) {
    val localDimensions = LocalDimensions.current
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
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))
        Text(
            text = stringResource(id = R.string.login_support_screen),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalXlarge))
        PrimaryTextField(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            onTextChange = {},
            placeholder = stringResource(id = R.string.enter_login)
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))
        PasswordTextField(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalTiny),
            onTextChange = {},
            description = stringResource(id = R.string.enter_password)
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalMedium))
        PrimaryTextButton(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = stringResource(id = R.string.login_button_text),
            onButtonClick = {},
            isEnabled = isLoginButtonEnabled
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))
        SecondaryTextButton(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = stringResource(id = R.string.without_login_button_text),
            onButtonClick = {},
            isEnabled = true
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))
        Row(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.without_accaunt),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(localDimensions.horizontalXTiny))
            ClickableText(
                text = AnnotatedString(
                    text = stringResource(id = R.string.sign_up)
                ),
                onClick = {
                    /* TODO: Handle click */
                },
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp
                )
            )
        }
        Spacer(modifier = Modifier.width(localDimensions.horizontalMedium))
        ClickableText(
            text = AnnotatedString(
                text = stringResource(id = R.string.reset_password)
            ),
            onClick = {
                /* TODO: Handle click */
            },
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(horizontal = localDimensions.horizontalMedium)
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
