package com.ortin.gesturetranslator.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions

@Composable
fun ProgressDialog(
    modifier: Modifier = Modifier,
    dialogText: String? = null
) {
    val localDimens = LocalDimensions.current
    val dialogMinSize = 150.dp

    Dialog(onDismissRequest = {}) {
        Column(
            modifier = modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.background)
                .defaultMinSize(dialogMinSize),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingAnimation(
                circleSize = localDimens.horizontalMedium,
                spaceBetween = localDimens.horizontalXTiny
            )
            dialogText?.also {
                Spacer(modifier = Modifier.height(localDimens.verticalSmall))
                Text(
                    text = dialogText,
                    modifier = Modifier
                        .padding(
                            horizontal = localDimens.horizontalMedium
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgressDialogPreview() {
    GestureTranslatorTheme {
        Surface {
            ProgressDialog(
                dialogText = "Падажжи загрузку плз",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}
