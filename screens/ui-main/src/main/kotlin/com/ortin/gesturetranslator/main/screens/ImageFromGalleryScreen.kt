package com.ortin.gesturetranslator.main.screens

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ortin.gesturetranslator.main.viewmodel.GalleryViewModel
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.RecognizedLetter
import com.ortin.gesturetranslator.ui.components.dialogs.CustomDialog
import com.ortin.gesturetranslator.ui.components.dialogs.ProgressDialog
import com.ortin.gesturetranslator.ui.components.dialogs.WarningDialog
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun ImageFromGalleryScreen(
    viewModel: GalleryViewModel,
    modifier: Modifier = Modifier,
) {
    val localDimensions = LocalDimensions.current
    val boxSize = 62.dp
    val imageData = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            if (it != null) {
                Glide.with(context)
                    .asBitmap()
                    .load(it)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            imageData.value = resource
                            viewModel.selectedImage(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            } else {
                imageData.value = null
                viewModel.someError()
            }
        }
    val state by viewModel.state.collectAsState()

    if (state.showDialogLoader) {
        ProgressDialog(dialogText = state.descriptionLoaderDialog)
    }

    CustomDialog(
        modifier = Modifier
            .pointerInput(Unit) { detectTapGestures { } }
            .shadow(8.dp, shape = RoundedCornerShape(localDimensions.horizontalMedium))
            .fillMaxWidth()
            .padding(horizontal = localDimensions.horizontalMedium)
            .clip(RoundedCornerShape(localDimensions.horizontalMedium))
            .background(MaterialTheme.colorScheme.surface),
        showDialog = state.showWarningDialog,
        onDismissRequest = { viewModel.closeWarning() }
    ) {
        WarningDialog(
            title = state.warningTitle,
            description = state.warningDescription,
            onConfirmButtonClick = { viewModel.closeWarning() },
            icon = R.drawable.icon_ortin_logo_without_text,
            onDismissRequest = { viewModel.closeWarning() }
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            bitmap = imageData.value?.asImageBitmap() ?: Bitmap.createBitmap(
                500,
                500,
                Bitmap.Config.ARGB_8888
            ).asImageBitmap(),
            contentDescription = state.recognizedLetter,
            modifier = Modifier
                .aspectRatio(1f)
                .padding(localDimensions.horizontalMedium)
                .clip(shape = MaterialTheme.shapes.medium)
                .clickable {
                    launcher.launch("image/*")
                },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1f))
        RecognizedLetter(
            modifier = Modifier
                .size(boxSize)
                .padding(bottom = localDimensions.horizontalMedium),
            letter = state.recognizedLetter,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
