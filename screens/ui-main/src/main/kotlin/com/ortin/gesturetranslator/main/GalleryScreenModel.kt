package com.ortin.gesturetranslator.main

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.ortin.gesturetranslator.common.presentation.ModelIntent
import com.ortin.gesturetranslator.common.presentation.UiState

@Immutable
sealed class GalleryScreenIntent : ModelIntent {

    data class OnRecognizedLetterChange(val letter: String) : GalleryScreenIntent()
    data class StartLoadDialog(val description: String) : GalleryScreenIntent()
    data object StopLoadDialog : GalleryScreenIntent()
}

@Immutable
data class GalleryScreenState(
    val image: Uri?,
    val recognizedLetter: String,
    val showDialogLoader: Boolean,
    val descriptionLoaderDialog: String
) : UiState {

    companion object {
        fun initial() = GalleryScreenState(
            image = null,
            recognizedLetter = "",
            showDialogLoader = false,
            descriptionLoaderDialog = "Идет настройка переводчика"
        )
    }
}
