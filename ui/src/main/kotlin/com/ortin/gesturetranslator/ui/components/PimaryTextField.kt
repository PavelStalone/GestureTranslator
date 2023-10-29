package com.ortin.gesturetranslator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ortin.gesturetranslator.ui.R

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String = "",
    placeholder: String? = null,
    supportText: String? = null,
    maxQuantityOfChar: Int? = null,
    maxLines: Int = 1,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChange: (String) -> Unit
) {
    var mutableValue by rememberSaveable {
        mutableStateOf(value)
    }
    val spacerModifier = Modifier.height(4.dp)
    Column(modifier = modifier) {
        title?.let {
            Text(text = title)
            Spacer(spacerModifier)
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mutableValue,
            onValueChange = {
                if (it.length <= (maxQuantityOfChar ?: (it.length + 1))) {
                    mutableValue = it
                    onTextChange(it)
                }
            },
            placeholder = {
                placeholder?.let {
                    Text(text = it)
                }
            },
            enabled = isEnabled,
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.secondary,
                disabledSupportingTextColor = Color.Blue,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorSupportingTextColor = MaterialTheme.colorScheme.error,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface
            ),
            supportingText = {
                supportText?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        textAlign = TextAlign.Start
                    )
                }
                maxQuantityOfChar?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(
                            id = R.string.limit_of_max_char,
                            mutableValue.length,
                            maxQuantityOfChar
                        ),
                        textAlign = TextAlign.End
                    )
                }
            },
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(size = 12.dp),
            trailingIcon = trailingIcon
        )
    }
}

@Preview
@Composable
fun PrimaryTextFieldPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        PrimaryTextField(onTextChange = {})
    }
}
