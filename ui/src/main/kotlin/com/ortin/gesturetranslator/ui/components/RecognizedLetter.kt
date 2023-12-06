package com.ortin.gesturetranslator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecognizedLetter(
    letter: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = letter,
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.primary),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
fun RecognizedLetterPreview() {
    RecognizedLetter(
        letter = "A",
        modifier = Modifier.width(48.dp)
    )
}
