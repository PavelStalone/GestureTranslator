package com.ortin.gesturetranslator.ui.components.cards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
fun SettingsCard(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    additionalContent: @Composable RowScope.() -> Unit
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLow
        )
    ) {
//        Spacer(modifier = Modifier.height(dimensions.verticalXTiny))
        Row(
            modifier = Modifier.padding(horizontal = dimensions.horizontalMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(3f),
                color = MaterialTheme.colorScheme.onSurface,
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            additionalContent()
        }
        Spacer(modifier = Modifier.height(dimensions.verticalXTiny))
        description?.let { text ->
            Text(
                modifier = Modifier.padding(horizontal = dimensions.horizontalMedium),
                text = text,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(dimensions.verticalStandard))
        }
    }
}

@Preview
@Composable
fun SettingsCardPreview() {
    SettingsCard(
        modifier = Modifier.padding(16.dp),
        title = "Включить светлую тему",
        description = "Нажимая на переключатель, Вы меняете основные цвета приложения со светлых на темные и наоборот",
        additionalContent = {}
    )
}
