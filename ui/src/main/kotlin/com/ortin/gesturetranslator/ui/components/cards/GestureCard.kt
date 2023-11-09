package com.ortin.gesturetranslator.ui.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R

@Composable
fun GestureCard(
    letter: String,
    @DrawableRes id: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(140.dp)
            .clip(MaterialTheme.shapes.medium),
    ) {
        Text(
            text = letter,
            modifier = modifier
                .size(40.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun GestureCardPreview() {
    GestureCard(
        letter = "A",
        id = R.drawable.a
    )
}
