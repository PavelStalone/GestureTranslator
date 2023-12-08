package com.ortin.gesturetranslator.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.ortin.gesturetranslator.main.viewmodel.SettingsScreenViewModel
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.cards.SettingsCard
import com.ortin.gesturetranslator.ui.components.combobox.ComboBox
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel,
    modifier: Modifier = Modifier,
    isPremium: Boolean = true, /* TODO: Replace with viewModel */
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by settingsScreenViewModel.state.collectAsState()
    val localDimensions = LocalDimensions.current

    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
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
                    checked = !state.isDarkMode,
                    onCheckedChange = {
                        settingsScreenViewModel.changeTheme()
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
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 32.sp)
            )
        }
        item {
            SettingsCard(
                title = stringResource(id = R.string.gpu_title),
                description = stringResource(id = R.string.gpu_description)
            ) {
                Switch(
                    checked = state.isGpu,
                    onCheckedChange = {
                        settingsScreenViewModel.changeProcessingTool()
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
                    checked = state.isShowPercent,
                    onCheckedChange = {
                        settingsScreenViewModel.changeShowPercent()
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
                    isEnabled = isPremium,
                    modifier = Modifier.fillMaxWidth(0.3f),
                    currentItem = state.speedFrameDetection,
                    onItemSelected = {
                        settingsScreenViewModel.changeSpeedFrameDetection(it.toInt())
                    }
                )
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                settingsScreenViewModel.onStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                settingsScreenViewModel.onStop()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
