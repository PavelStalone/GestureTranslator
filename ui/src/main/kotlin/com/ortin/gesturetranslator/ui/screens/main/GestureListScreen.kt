package com.ortin.gesturetranslator.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

@Composable
fun GestureListScreen(
    modifier: Modifier = Modifier
) {
    val localDimensions = LocalDimensions.current
    val list = listOf<String>(
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
        "Ю"
    )

    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = localDimensions.horizontalLarge,
                vertical = localDimensions.verticalPreExtraLarge
            )
    ) {
        itemsIndexed(
            list
        ) { _, item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GestureCard(
                    letter = item,
                    imageId = R.drawable.a,
                    modifier = Modifier.size(140.dp)
                )
                GestureCard(
                    letter = item,
                    imageId = R.drawable.a,
                    modifier = Modifier.size(140.dp)
                )
            }
            Spacer(modifier = Modifier.height(localDimensions.verticalLarge))
        }
        item {
            GestureCard(
                letter = "Я",
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
