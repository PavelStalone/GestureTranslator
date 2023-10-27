package com.ortin.gesturetranslator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.PasswordTextField
import com.ortin.gesturetranslator.ui.components.PrimaryTextButton
import com.ortin.gesturetranslator.ui.components.PrimaryTextField

@Preview
@Composable
fun LoginScreen() {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_ortin_logo_light),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Войдите, чтобы пользоваться\n полным функционалом приложения",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(48.dp))
            PrimaryTextField(
                onTextChange = {},
                description = "Введите логин"
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                onTextChange = {},
                description = "Введите пароль"
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryTextButton(
                text = "Войти",
                onButtonClick = {},
                isEnabled = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryTextButton(
                text = "Продолжить без регистрации",
                onButtonClick = {},
                isEnabled = false
            )
        }
    }
}
