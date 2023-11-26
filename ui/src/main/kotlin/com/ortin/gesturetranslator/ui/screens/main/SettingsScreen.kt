package com.ortin.gesturetranslator.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.components.cards.SettingsCard
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isPremium: Boolean = false /* TODO: Replace with viewModel */
) {
    var isLightTheme by remember { mutableStateOf(true) }
    var isGpuOn by remember { mutableStateOf(false) }
    var isPercentsVisible by remember { mutableStateOf(false) }
    var recognitionSpeed by remember { mutableIntStateOf(30) }
    var isExpanded by remember { mutableStateOf(true) }

    val speedConstants = listOf(30, 45, 60)

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Основные")
        SettingsCard(
            title = "Включить светлую тему",
            description = "Нажимая на переключатель, Вы меняете основные цвета приложения со светлых на темные и наоборот"
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
        Text(text = "По подписке")

        SettingsCard(
            title = "Включить обработку по GPU",
            description = "Нажимая на переключатель, Вы меняете основные цвета приложения со светлых на темные и наоборот"
        ) {
            Switch(
                checked = isGpuOn,
                onCheckedChange = {
                    isGpuOn = !isGpuOn
                    /* TODO: Handle click */
                },
                colors = SwitchDefaults.colors(
                    uncheckedTrackColor = surfaceContainerLow
                )
            )
        }
        SettingsCard(
            title = "Включить показ процентов",
            description = "Нажимая на переключатель, Вы включаете или выключаете отображение процента точности распознавания жеста"
        ) {
            Switch(
                checked = isGpuOn,
                onCheckedChange = {
                    isGpuOn = !isGpuOn
                    /* TODO: Handle click */
                },
                colors = SwitchDefaults.colors(
                    uncheckedTrackColor = surfaceContainerLow
                )
            )
        }
        SettingsCard(
            title = "Скорость распознавания",
            description = "В выпадающем меню Вы можете выбрать с какой скоростью будут считываться жесты с камеры"
        ) {
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = !isExpanded
                    /*TODO*/
                }
            ) {
                speedConstants.forEach {
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    GestureTranslatorTheme {
        Surface {
            SettingsScreen()
        }
    }
}