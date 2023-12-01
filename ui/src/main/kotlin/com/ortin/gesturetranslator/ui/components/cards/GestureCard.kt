package com.ortin.gesturetranslator.ui.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun GestureCard(
    letter: String,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        val boxSize = 40.dp

        Image(
            painter = painterResource(id = imageId),
            contentDescription = letter,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = letter,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
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
                imageId = R.drawable.a,
                modifier = Modifier.size(140.dp)
            )
        }
    }
}
