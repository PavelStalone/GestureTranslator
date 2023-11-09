package com.ortin.gesturetranslator.ui.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun GestureCard(
    letter: String,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(140.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = letter
            )
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
}

@Preview
@Composable
fun GestureCardPreview() {
    GestureTranslatorTheme {
        Surface {
            GestureCard(
                letter = "A",
                imageId = R.drawable.a
            )
        }
    }
}
