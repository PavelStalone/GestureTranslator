package com.ortin.gesturetranslator.main.screens

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.ortin.gesturetranslator.ui.components.RecognizedLetter
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun ImageFromGalleryScreen(
    modifier: Modifier = Modifier,
    letter: String = "A",
    @DrawableRes imageId: Int = R.drawable.a,
) {
    val localDimensions = LocalDimensions.current
    val boxSize = 62.dp

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(localDimensions.verticalSmall))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = letter,
            modifier = Modifier
                .padding(horizontal = localDimensions.horizontalMedium)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1f))
        RecognizedLetter(
            modifier = Modifier.size(boxSize),
            letter = letter,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(localDimensions.verticalMedium))
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun ImageFromGalleryScreenDarkPreview() {
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
