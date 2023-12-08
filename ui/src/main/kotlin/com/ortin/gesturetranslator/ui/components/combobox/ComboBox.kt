package com.ortin.gesturetranslator.ui.components.combobox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.onSurfaceContainerLow
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

private val listOfItems = listOf("10", "15", "30", "35", "40")

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ComboBox(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    currentItem: Int = 0,
    onItemSelected: (String) -> Unit = {}
) {
    val localDimens = LocalDimensions.current
    var expanded by remember { mutableStateOf(false) }
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
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(vertical = localDimens.verticalTiny)
                    .menuAnchor()
            ) {
                Text(
                    text = currentItem.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isItemSelected && isEnabled) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        if (!isEnabled) onSurfaceContainerLow
                        else MaterialTheme.colorScheme.onSecondaryContainer
                    }
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(if (expanded) 180f else 0f),
                    tint = if (isEnabled) {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    } else onSurfaceContainerLow
                )
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOfItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
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
                modifier = Modifier.width(100.dp)
            )
        }
    }
}
