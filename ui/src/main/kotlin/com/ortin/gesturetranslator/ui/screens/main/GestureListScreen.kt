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

val gesturesList = listOf(
    Gesture("А", R.drawable.a),
    Gesture("Б", R.drawable.b),
    Gesture("В", R.drawable.v),
    Gesture("Г", R.drawable.g),
    Gesture("Д", R.drawable.d),
    Gesture("Е", R.drawable.e),
    Gesture("Ж", R.drawable.zh),
    Gesture("З", R.drawable.z),
    Gesture("И", R.drawable.i),
    Gesture("К", R.drawable.k),
    Gesture("Л", R.drawable.l),
    Gesture("М", R.drawable.m),
    Gesture("Н", R.drawable.n),
    Gesture("О", R.drawable.o),
    Gesture("П", R.drawable.p),
    Gesture("Р", R.drawable.r),
    Gesture("С", R.drawable.s),
    Gesture("Т", R.drawable.t),
    Gesture("У", R.drawable.u),
    Gesture("Ф", R.drawable.f),
    Gesture("Х", R.drawable.h),
    Gesture("Ц", R.drawable.c),
    Gesture("Ч", R.drawable.ch),
    Gesture("Ш", R.drawable.sh),
    Gesture("Ы", R.drawable.bi),
    Gesture("Ь", R.drawable.soft_sign),
    Gesture("Э", R.drawable.e),
    Gesture("Ю", R.drawable.yu),
    Gesture("Я", R.drawable.ya),
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
        items(gesturesList) { item ->
            GestureCard(
                letter = item.name,
                imageId = item.imageId,
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

data class Gesture(
    val name: String,
    val imageId: Int
)
