package com.ortin.gesturetranslator.main

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.ortin.gesturetranslator.common.presentation.ModelIntent
import com.ortin.gesturetranslator.common.presentation.UiState

@Immutable
sealed class MainTranslatorScreenIntent : ModelIntent {

    data class OnTextTranslatingChange(val text: String) : MainTranslatorScreenIntent()
    data class OnImageChange(val newImage: Bitmap) : MainTranslatorScreenIntent()
    data class OnRecognizedLetterChange(val letter: String) : MainTranslatorScreenIntent()
    data class StartLoaderDialog(val description: String) : MainTranslatorScreenIntent()
    data object StopLoaderDialog : MainTranslatorScreenIntent()
}

@Immutable
data class MainTranslatorScreenState(
    val image: Bitmap,
    val recognizedLetter: String,
    val translatedText: String,
    val showDialogLoader: Boolean,
    val descriptionLoaderDialog: String
) : UiState {

    companion object {
        fun initial() = MainTranslatorScreenState(
            image = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888),
            recognizedLetter = "",
            translatedText = "",
            showDialogLoader = true,
            descriptionLoaderDialog = "Идет настройка переводчика"
        )
    }
}
