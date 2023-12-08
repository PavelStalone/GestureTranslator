package com.ortin.gesturetranslator.main

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.ortin.gesturetranslator.common.presentation.ModelIntent
import com.ortin.gesturetranslator.common.presentation.UiState

@Immutable
sealed class GalleryScreenIntent : ModelIntent {

    data class OnRecognizedLetterChange(val letter: String) : GalleryScreenIntent()
    data class StartLoadDialog(val description: String) : GalleryScreenIntent()
    data class ShowWarningDialog(val title: String, val description: String) : GalleryScreenIntent()
    data object StopLoadDialog : GalleryScreenIntent()
    data object CloseWarningDialog : GalleryScreenIntent()
}

@Immutable
data class GalleryScreenState(
    val image: Uri?,
    val recognizedLetter: String,
    val showDialogLoader: Boolean,
    val descriptionLoaderDialog: String,
    val showWarningDialog: Boolean,
    val warningTitle: String,
    val warningDescription: String
) : UiState {

    companion object {
        fun initial() = GalleryScreenState(
            image = null,
            recognizedLetter = "",
            showDialogLoader = false,
            descriptionLoaderDialog = "Идет настройка переводчика",
            showWarningDialog = false,
            warningTitle = "Что-то пошло не так",
            warningDescription = "Не волнуйтесь, это просто ошибка"
        )
    }
}
