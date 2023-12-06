package com.ortin.gesturetranslator.ui.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.navbar.BottomNavigationBar
import com.ortin.gesturetranslator.ui.components.navbar.navigationBarItems
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun ImageFromGalleryScreen(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    letter: String
) {
    val localDimensions = LocalDimensions.current
    val spacerHeight = 160.dp
    val imageSize = 376.dp
    val boxSize = 62.dp

    Column(
        modifier.padding(horizontal = localDimensions.horizontalMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = letter,
            modifier = Modifier
                .size(imageSize)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(spacerHeight))
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
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        Spacer(modifier = Modifier.height(localDimensions.verticalMedium))
        BottomNavigationBar(items = navigationBarItems)
    }
}

@Preview
@Composable
fun ImageFromGalleryScreenLightPreview() {
    GestureTranslatorTheme {
        Surface {
            ImageFromGalleryScreen(
                modifier = Modifier.fillMaxSize(),
                imageId = R.drawable.a,
                letter = "A"
            )
        }
    }
}

@Preview
@Composable
fun ImageFromGalleryScreenDarkPreview() {
    GestureTranslatorTheme(darkTheme = true) {
        Surface {
            ImageFromGalleryScreen(
                modifier = Modifier.fillMaxSize(),
                imageId = R.drawable.a,
                letter = "A"
            )
        }
    }
}
