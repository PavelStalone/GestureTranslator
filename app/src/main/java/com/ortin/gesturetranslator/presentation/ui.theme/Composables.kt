package com.ortin.gesturetranslator.presentation.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun SurfaceText(text: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 2.dp
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun ThemedButton(text: String, isPrimary: Boolean, isCentered: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = if (isPrimary) ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        else ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),

        contentPadding = PaddingValues(20.dp, 15.dp),
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            color = if (isPrimary)
                MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSecondaryContainer,
        )
        if (!isCentered) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                Icons.Default.KeyboardArrowRight,
                contentDescription = text,
                colorFilter = ColorFilter.tint(
                    if (isPrimary)
                        MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSecondaryContainer,
                )
            )
        }
    }
}