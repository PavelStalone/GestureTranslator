package com.ortin.gesturetranslator.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.cards.GestureCard
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

private val list = listOf<String>(
    "А",
    "Б",
    "В",
    "Г",
    "Д",
    "Е",
    "Ж",
    "З",
    "И",
    "К",
    "Л",
    "М",
    "Н",
    "О",
    "П",
    "Р",
    "С",
    "Т",
    "У",
    "Ф",
    "Х",
    "Ц",
    "Ч",
    "Ш",
    "Ы",
    "Ь",
    "Э",
    "Ю",
    "Я"
)

@Composable
fun GestureListScreen(
    modifier: Modifier = Modifier
) {
    val localDimensions = LocalDimensions.current

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(space = localDimensions.verticalLarge),
        horizontalArrangement = Arrangement.spacedBy(space = localDimensions.horizontalLarge),
        contentPadding = PaddingValues(localDimensions.horizontalLarge)
    ) {
        items(
            list
        ) { item ->
            GestureCard(
                letter = item,
                imageId = R.drawable.a,
                modifier = Modifier.size(140.dp)
            )
        }
    }
}

@Preview
@Composable
fun GestureListScreenPreviewLight() {
    GestureTranslatorTheme {
        Surface {
            GestureListScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

@Preview
@Composable
fun GestureListScreenPreviewDark() {
    GestureTranslatorTheme(darkTheme = true) {
        Surface {
            GestureListScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}
