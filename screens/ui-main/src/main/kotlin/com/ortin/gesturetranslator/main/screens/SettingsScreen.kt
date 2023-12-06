package com.ortin.gesturetranslator.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.cards.SettingsCard
import com.ortin.gesturetranslator.ui.components.combobox.ComboBox
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isPremium: Boolean = false /* TODO: Replace with viewModel */
) {
    var isLightTheme by remember { mutableStateOf(true) }
    var isGpuOn by remember { mutableStateOf(false) }
    var isPercentsVisible by remember { mutableStateOf(false) }

    val speedConstants = listOf("10", "15", "20", "25", "30")
    val localDimensions = LocalDimensions.current

    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(localDimensions.horizontalMedium),
        verticalArrangement = Arrangement.spacedBy(localDimensions.verticalStandard)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.main_settings),
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 32.sp)
            )
        }
        item {
            SettingsCard(
                title = stringResource(id = R.string.theme_title),
                description = stringResource(id = R.string.theme_description)
            ) {
                Switch(
                    checked = isLightTheme,
                    onCheckedChange = {
                        isLightTheme = !isLightTheme
                        /* TODO: Handle click */
                    },
                    colors = SwitchDefaults.colors(
                        uncheckedTrackColor = surfaceContainerLow
                    )
                )
            }
        }
        item {
            Text(
                text = stringResource(id = R.string.by_premium),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            SettingsCard(
                title = stringResource(id = R.string.gpu_title),
                description = stringResource(id = R.string.gpu_description)
            ) {
                Switch(
                    checked = isGpuOn,
                    onCheckedChange = {
                        isGpuOn = !isGpuOn
                        /* TODO: Handle click */
                    },
                    enabled = isPremium,
                    colors = SwitchDefaults.colors(
                        uncheckedTrackColor = surfaceContainerLow
                    )
                )
            }
        }
        item {
            SettingsCard(
                title = stringResource(id = R.string.percents_title),
                description = stringResource(id = R.string.percents_description)
            ) {
                Switch(
                    checked = isPercentsVisible,
                    onCheckedChange = {
                        isPercentsVisible = !isPercentsVisible
                        /* TODO: Handle click */
                    },
                    enabled = isPremium,
                    colors = SwitchDefaults.colors(
                        uncheckedTrackColor = surfaceContainerLow
                    )
                )
            }
        }
        item {
            SettingsCard(
                title = stringResource(id = R.string.recognition_speed_title),
                description = stringResource(id = R.string.recognition_speed_description)
            ) {
                ComboBox(
                    listOfItems = speedConstants,
                    isEnabled = isPremium,
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onItemSelected = { /* TODO: Handle click */ }
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    GestureTranslatorTheme {
        Surface {
            SettingsScreen(isPremium = true)
        }
    }
}
