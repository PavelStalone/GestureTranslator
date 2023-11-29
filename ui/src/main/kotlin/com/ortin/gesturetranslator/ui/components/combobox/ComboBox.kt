package com.ortin.gesturetranslator.ui.components.combobox


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ComboBox(
    listOfItems: List<String>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    onItemSelected: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listOfItems[0]) }

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
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    focusedTextColor = if(isEnabled) {
                        MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.onSecondaryContainer,
                    unfocusedContainerColor = surfaceContainerLow,
                    focusedContainerColor = surfaceContainerLow,
                    unfocusedIndicatorColor = surfaceContainerLow,
                    focusedIndicatorColor = surfaceContainerLow
                ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = !expanded)
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOfItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            onItemSelected(item)
                            expanded = false
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
