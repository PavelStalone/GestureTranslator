package com.ortin.gesturetranslator.presentation.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SurfaceText(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
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

@Preview
@Composable
fun SurfaceTextPreview() {
    OrtinTheme {
        SurfaceText(text = "")
    }
}

@Composable
fun MainButton(
    text: String,
    isPrimary: Boolean,
    isCentered: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = if (isPrimary) ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        else {
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        },
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
        enabled = enabled,
        onClick = { onClick() }
    ) {
        val color = if (!enabled) MaterialTheme.colorScheme.onSurfaceVariant
            .copy(alpha = 0.5f)
        else if (isPrimary) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSecondaryContainer
        Text(
            text = text,
            color = color
        )
        if (!isCentered) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                Icons.Default.KeyboardArrowRight,
                contentDescription = text,
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

@Preview
@Composable
fun MainButtonsPreview() {
    OrtinTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                text = "Example text",
                isPrimary = true,
                isCentered = true,
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                text = "Example text",
                isPrimary = false,
                isCentered = true,
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                text = "Example text",
                isPrimary = true,
                isCentered = false,
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                text = "Example text",
                isPrimary = false,
                isCentered = false,
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                text = "Example text",
                isPrimary = false,
                isCentered = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                text = "Example text",
                isPrimary = false,
                isCentered = false,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
