package com.ortin.gesturetranslator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.buttons.PrimaryTextButton
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow

@Composable
fun DeveloperCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    val localDimensions = LocalDimensions.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.5f)
        )
    ) {
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))
        Text(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))
        Text(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.padding(
                horizontal = localDimensions.horizontalMedium,
                vertical = localDimensions.verticalTiny
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_ortin_logo_without_text),
                contentDescription = null
            )
            PrimaryTextButton(
                modifier = Modifier.weight(1f),
                text = "GitHub",
                onClick = {}
            )
            PrimaryTextButton(
                modifier = Modifier.weight(1f),
                text = "Email",
                onClick = {}
            )

        }
    }
}

@Preview
@Composable
fun DeveloperCardPreview() {
    MaterialTheme {
        Surface {
            DeveloperCard(
                modifier = Modifier.padding(16.dp),
                title = "PavelStalone",
                description = "Вообще красавчик машина убийца"
            )
        }
    }
}
