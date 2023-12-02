package com.ortin.gesturetranslator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.buttons.PrimaryTextButton
import com.ortin.gesturetranslator.ui.components.buttons.SecondaryTextButton
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun MainMenuScreen (
    isLoggedIn: Boolean,
    isPremium: Boolean,
    userLogin: String,
    modifier: Modifier = Modifier,
    userImageID: Int = R.drawable.icon_ortin_logo_without_text
) {
    Column(modifier = modifier) {
        val columnArrangement = LocalDimensions.current.verticalStandard
        val buttonIcon = Icons.Default.KeyboardArrowRight
        val userIconSize = 110.dp

        Column(
            verticalArrangement = Arrangement.spacedBy(columnArrangement),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalDimensions.current.verticalStandard)
        ) {
            if (isLoggedIn) {
                Image(
                    painter = painterResource(id = userImageID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(userIconSize)
                        .clip(CircleShape)
                )
                Text(
                    text = userLogin,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall
                )
            } else {
                Surface(
                    tonalElevation = 2.dp,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = stringResource(id = R.string.not_logged_in_help_text),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(LocalDimensions.current.horizontalMedium)
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(columnArrangement),
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalDimensions.current.verticalStandard)
        ) {
            if (isLoggedIn) {
                if (isPremium) {
                    PrimaryTextButton(
                        text = stringResource(id = R.string.main_menu_is_premium),
                        rightIcon = buttonIcon,
                        onClick = { /* TODO: Handle clicks */ },
                    )
                } else {
                    PrimaryTextButton(
                        text = stringResource(id = R.string.main_menu_buy_premium),
                        rightIcon = buttonIcon,
                        onClick = { /* TODO: Handle clicks */ },
                    )
                }
                SecondaryTextButton(
                    text = stringResource(id = R.string.main_menu_account_settings),
                    rightIcon = buttonIcon,
                    onClick = { /* TODO: Handle clicks */ },
                )
            } else {
                PrimaryTextButton(
                    text = stringResource(id = R.string.main_menu_log_in),
                    rightIcon = buttonIcon,
                    onClick = { /* TODO: Handle clicks */ },
                )
            }
            SecondaryTextButton(
                text = stringResource(id = R.string.main_menu_app_settings),
                rightIcon = buttonIcon,
                onClick = { /* TODO: Handle clicks */ },
            )
            SecondaryTextButton(
                text = stringResource(id = R.string.main_menu_bugreport),
                rightIcon = buttonIcon,
                onClick = { /* TODO: Handle clicks */ },
            )
            SecondaryTextButton(
                text = stringResource(id = R.string.main_menu_about),
                rightIcon = buttonIcon,
                onClick = { /* TODO: Handle clicks */ },
            )
        }
    }
}

@Preview
@Composable
fun MainMenuScreenNotLoggedInPreview() {
    GestureTranslatorTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            MainMenuScreen(
                isLoggedIn = false,
                isPremium = false,
                userImageID = R.drawable.icon_ortin_logo_without_text,
                userLogin = "Username",
            )
        }
    }
}

@Preview
@Composable
fun MainMenuScreenLoggedInPreview() {
    GestureTranslatorTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            MainMenuScreen(
                isLoggedIn = true,
                isPremium = true,
                userImageID = R.drawable.icon_ortin_logo_without_text,
                userLogin = "Username",
            )
        }
    }
}
