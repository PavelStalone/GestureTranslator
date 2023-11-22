package com.ortin.gesturetranslator.ui.screens

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.buttons.PrimaryTextButton
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevelopersInfoListScreen(

) {

    var topBar by remember {
        mutableStateOf("О приложении")
    }
    val lazyListState = rememberLazyListState()
    val firstItemTranslationY by remember {
        derivedStateOf {
            if (lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0) {
                lazyListState.firstVisibleItemScrollOffset * 0.4f
            } else {
                0f
            }
        }
    }

    val firstItemVisibility by remember {
        derivedStateOf {
            if (lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0) {
                1 - lazyListState.firstVisibleItemScrollOffset.toFloat() / lazyListState.layoutInfo.visibleItemsInfo[0].size
            } else {
                1f
            }
        }
    }

    val isDragged by lazyListState.interactionSource.collectIsDraggedAsState()
    val isScrollingUp = lazyListState.isScrollingUp()

    LaunchedEffect(isDragged) {

        if (isDragged) {
            return@LaunchedEffect
        }

        if (lazyListState.firstVisibleItemIndex > 1) {
            if (topBar != "Разработчики") {
                topBar = "Разработчики"
            }
            return@LaunchedEffect
        }

        if (!isScrollingUp) {
            topBar = "Разработчики"
            lazyListState.animateScrollToItem(2)
        } else {
            topBar = "О приложении"
            lazyListState.animateScrollToItem(0)
        }

//        if (!isScrollingUp && lazyListState.firstVisibleItemIndex in 0 .. 1) {
//            topBar = "Разработчики"
//            lazyListState.animateScrollToItem(2)
//        }
//
//        if (lazyListState.firstVisibleItemIndex < 2 && isScrollingUp) {
//            topBar = "О приложении"
//            lazyListState.animateScrollToItem(0)
//        }
//
//        if (lazyListState.firstVisibleItemIndex > 1) {
//            topBar = "Разработчики"
//        }
//
//        if (lazyListState.firstVisibleItemIndex == 0) {
//            topBar = "О приложении"
//        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title =
                {
                    AnimatedText(text = topBar)
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.padding(horizontal = 8.dp),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            translationY = firstItemTranslationY
                            alpha = firstItemVisibility
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_ortin_logo_without_text),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "Gesture Translator - настолько класная прилажуха что даже сложно придумать соизмеримо класное описание, так что я над ним еще подумаю, но вы не уходите пока что",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryTextButton(
                        text = "Сайт приложения",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryTextButton(
                        text = "GitHub",
                        onClick = {}
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Разработчики",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 32.sp
                        )
                    )
                }
            }
            items(6) {
                DeveloperCard(
                    title = "PavelStalone",
                    description = "Вообще красавчик машина убийца"
                )

            }
        }
    }
}
@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}
//@Composable
//fun LazyListState.isScrollingDown(): Boolean {
//    val offset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
//    return remember(this) { derivedStateOf { (firstVisibleItemScrollOffset - offset) > 0 } }.value
//}
//
//@Composable
//fun LazyListState.isScrollingUp(): Boolean {
//    val offset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
//    return remember(this) { derivedStateOf { (firstVisibleItemScrollOffset - offset) < 0 } }.value
//}

@Preview
@Composable
fun Preview() {
    GestureTranslatorTheme {
        Surface {
            DevelopersInfoListScreen()
        }
    }
}
