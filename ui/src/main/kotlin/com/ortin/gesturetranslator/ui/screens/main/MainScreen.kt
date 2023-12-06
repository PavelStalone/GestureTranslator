package com.ortin.gesturetranslator.ui.screens.main

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.ortin.gesturetranslator.ui.AdaptiveTestPreviews
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.RecognizedLetter
import com.ortin.gesturetranslator.ui.components.buttons.RadioButton
import com.ortin.gesturetranslator.ui.components.text.ScrollableText
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(
    image: Bitmap,
    recognizedLetter: String,
    onStatusRecognitionChanged: (statusRecognition: Boolean) -> Unit,
    onStatusCorrectiveChanged: (statusCorrective: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val localDimensions = LocalDimensions.current
    val density = LocalDensity.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        val scaffoldState = rememberBottomSheetScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        val isBehaviorOnExpanded by remember(scaffoldState) {
            derivedStateOf { scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded }
        }

        var sheetSize by remember {
            mutableStateOf(localDimensions.sheetPeekHeight)
        }
        var correctChecked by remember {
            mutableStateOf(false)
        }
        var translateChecked by remember {
            mutableStateOf(false)
        }

        BottomSheetScaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = MaterialTheme.colorScheme.surface,
            sheetContent = {
                ScrollableText(
                    modifier = Modifier
                        .padding(horizontal = localDimensions.horizontalPreLarge)
                        .fillMaxSize(),
                    textState = "Моя говорить твоя не понимать что я говорить что твоя не понимать",
                    scrollable = !isBehaviorOnExpanded
                )
            },
            scaffoldState = scaffoldState,
            sheetShape = ShapeDefaults.ExtraLarge.copy(
                bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp)
            ),
            sheetPeekHeight = sheetSize + localDimensions.sheetPeekHeight
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(bottom = innerPadding.calculateBottomPadding() - 28.dp)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    bitmap = image.asImageBitmap(),
                    contentDescription = null
                )
                RecognizedLetter(
                    modifier = Modifier
                        .width(48.dp)
                        .padding(bottom = 40.dp),
                    letter = recognizedLetter
                )
            }
        }

        // Кастомный layout для отрисовки одинакового размера кнопок и расчета адаптивного расположения
        Layout(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = localDimensions.verticalSmall)
            .onSizeChanged {
                sheetSize = with(density) { it.height.toDp() }
            },
            measurePolicy = { measurables, constraints ->
                val minWidth = measurables.maxOf { it.minIntrinsicWidth(0) }
                val placeables = measurables.map { measurable ->
                    measurable.measure(constraints.copy(minWidth = minWidth, minHeight = 0))
                }.toMutableList()
                val sumWidhtPlaceables = placeables.sumOf { it.width }
                val height = if (sumWidhtPlaceables > constraints.maxWidth) {
                    placeables.sumOf { it.height }
                } else {
                    placeables.maxOf { it.height }
                }

                layout(constraints.maxWidth, height) {
                    if (sumWidhtPlaceables > constraints.maxWidth) {
                        var y = 0

                        placeables.forEach { placeable ->
                            placeable.placeRelative(
                                constraints.maxWidth / 2 - placeable.width / 2, y
                            )
                            y += placeable.height
                        }
                    } else {
                        val offset =
                            (constraints.maxWidth - sumWidhtPlaceables) / max(placeables.size, 2)
                        var x = offset

                        placeables.forEach { placeable ->
                            placeable.placeRelative(x, 0)
                            x += placeable.width
                        }
                    }
                }
            },
            content = {
                RadioButton(
                    modifier = Modifier.padding(horizontal = localDimensions.horizontalTiny),
                    checked = !isBehaviorOnExpanded || translateChecked,
                    text = "Распознавание",
                    onClick = {
                        coroutineScope.launch {
                            translateChecked = it
                            if (it) {
                                scaffoldState.bottomSheetState.partialExpand()
                            } else {
                                scaffoldState.bottomSheetState.expand()
                            }
                            onStatusRecognitionChanged(it)
                        }
                    }
                )
                AnimatedVisibility(
                    visible = isBehaviorOnExpanded,
                    enter = slideInHorizontally { with(density) { 100.dp.roundToPx() } }
                            + fadeIn(initialAlpha = 0.3f),
                    exit = slideOutHorizontally { with(density) { 200.dp.roundToPx() } }
                ) {
                    RadioButton(
                        modifier = Modifier.padding(horizontal = localDimensions.horizontalTiny),
                        checked = correctChecked && isBehaviorOnExpanded,
                        text = "Автоисправление",
                        onClick = {
                            correctChecked = it
                            onStatusCorrectiveChanged(it)
                        }
                    )
                }
            }
        )
    }
}

@Composable
@AdaptiveTestPreviews
fun MainScreenPreview() {
    GestureTranslatorTheme {
        Surface {
            MainScreen(
                onStatusCorrectiveChanged = {},
                onStatusRecognitionChanged = {},
                image = LocalContext.current.getDrawable(R.drawable.a)
                    ?.toBitmap(500, 500, Bitmap.Config.ARGB_8888) ?: Bitmap.createBitmap(
                    500,
                    500,
                    Bitmap.Config.ARGB_8888
                ),
                recognizedLetter = "A"
            )
        }
    }
}
