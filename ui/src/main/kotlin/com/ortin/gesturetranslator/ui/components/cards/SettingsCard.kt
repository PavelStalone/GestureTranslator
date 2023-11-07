package com.ortin.gesturetranslator.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun SettingsCard(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    composeFunc: @Composable RowScope.() -> Unit = {
        var checked by rememberSaveable {
            mutableStateOf(false)
        }

        Switch(
            modifier = Modifier.weight(1f),
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
) {
    val dimensions = LocalDimensions.current

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(dimensions.verticalStandard))
        Row(
            modifier = Modifier
                .padding(horizontal = dimensions.horizontalMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(3f),
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
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(400),
                fontSize = 15.sp
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
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color(0xFF757680)),
        title = "Включить светлую тему",
        description = "Нажимая на переключатель, Вы меняете основные цвета приложения со светлых на темные и наоборот"
    )
}
