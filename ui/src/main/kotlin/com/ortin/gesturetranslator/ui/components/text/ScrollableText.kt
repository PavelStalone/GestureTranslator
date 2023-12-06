package com.ortin.gesturetranslator.ui.components.text

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScrollableText(
    modifier: Modifier = Modifier,
    textState: String,
    scrollable: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall
) {
    val coroutineScope = rememberCoroutineScope()
    val textScroll = rememberScrollState()

    Text(
        modifier = modifier
            .let {
                if (scrollable) it.horizontalScroll(textScroll) else it
            },
        text = textState,
        style = textStyle
    )
    coroutineScope.launch { textScroll.animateScrollTo(textScroll.maxValue) }
}

@Composable
@Preview
fun ScrollableTextPreview() {
    GestureTranslatorTheme {
        val textState = remember {
            mutableStateOf("None")
        }
        Column {
            TextField(value = textState.value, onValueChange = { textState.value = it })
            ScrollableText(textState = textState.value)
        }
    }
}
