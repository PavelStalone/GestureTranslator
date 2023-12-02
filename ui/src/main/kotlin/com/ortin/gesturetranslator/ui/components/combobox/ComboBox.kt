package com.ortin.gesturetranslator.ui.components.combobox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ComboBox(
    listOfItems: List<String>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    onItemSelected: (String) -> Unit = {}
) {
    val localDimens = LocalDimensions.current
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(listOfItems[0]) }
    var isItemSelected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (isEnabled) {
                    expanded = !expanded
                }
            }
        ) {
            Row(
                modifier = Modifier
                    .background(surfaceContainerLow)
                    .padding(vertical = localDimens.verticalTiny)
            ) {
                Text(
                    text = selectedItem,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .menuAnchor(),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isItemSelected && isEnabled) {
                        MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.onSecondaryContainer
                )
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOfItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedItem = item
                            onItemSelected(item)
                            expanded = false
                            isItemSelected = true
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ComboBoxPreview() {
    GestureTranslatorTheme {
        Surface {
            ComboBox(
                modifier = Modifier.width(100.dp),
                listOfItems = listOf("10", "15", "30", "35", "40")
            )
        }
    }
}
