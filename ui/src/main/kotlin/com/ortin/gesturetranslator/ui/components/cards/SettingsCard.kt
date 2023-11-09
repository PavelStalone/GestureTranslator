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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
fun SettingsCard(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    composeFunc: @Composable RowScope.() -> Unit
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLow
        )
    ) {
        Spacer(modifier = Modifier.height(dimensions.verticalStandard))
        Row(
            modifier = Modifier
                .padding(horizontal = dimensions.horizontalMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(3f),
                color = MaterialTheme.colorScheme.onSurface,
                text = title,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(600),
                fontSize = 20.sp
            )
            composeFunc()
        }
        Spacer(modifier = Modifier.height(dimensions.verticalStandard))
        description?.let {
            Text(
                modifier = Modifier.padding(horizontal = dimensions.horizontalMedium),
                text = it,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(400),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(dimensions.verticalStandard))
        }
    }
}

@Preview
@Composable
fun SettingsCardPreview() {
    SettingsCard(
        modifier = Modifier
            .padding(16.dp),
        title = "Включить светлую тему",
        description = "Нажимая на переключатель, Вы меняете основные цвета приложения со светлых на темные и наоборот",
        composeFunc = {}
    )
}