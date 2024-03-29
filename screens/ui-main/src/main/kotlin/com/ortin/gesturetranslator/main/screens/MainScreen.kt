package com.ortin.gesturetranslator.main.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ortin.gesturetranslator.main.R
import com.ortin.gesturetranslator.main.viewmodel.MainTranslatorViewModel
import com.ortin.gesturetranslator.ui.components.RecognizedLetter
import com.ortin.gesturetranslator.ui.components.buttons.RadioButton
import com.ortin.gesturetranslator.ui.components.dialogs.CustomDialog
import com.ortin.gesturetranslator.ui.components.dialogs.ProgressDialog
import com.ortin.gesturetranslator.ui.components.dialogs.WarningDialog
import com.ortin.gesturetranslator.ui.components.text.ScrollableText
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(
    viewModel: MainTranslatorViewModel
) {
    viewModel.bindLifeCycle(LocalLifecycleOwner.current)

    var permissionGranted by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val localDimensions = LocalDimensions.current
    val density = LocalDensity.current
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
            icon = com.ortin.gesturetranslator.ui.R.drawable.icon_ortin_logo_without_text,
            onDismissRequest = { viewModel.closeWarning() }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
        )
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        var sheetSize by remember {
            mutableStateOf(localDimensions.sheetPeekHeight)
        }
        var correctChecked by remember {
            mutableStateOf(false)
        }
        var translateChecked by remember {
            mutableStateOf(false)
        }

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { result ->
                permissionGranted = result
                if (!result) {
                    translateChecked = false
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            }

        val isBehaviorOnExpanded by remember(scaffoldState) {
            derivedStateOf {
                (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded).also {
                    translateChecked = !it
                    if (permissionGranted) viewModel.onTranslatingStatusChanged(!it)
                }
            }
        }

        if (translateChecked && !permissionGranted) {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) -> {
                    permissionGranted = true
                }

                else -> {
                    launcher.launch(Manifest.permission.CAMERA)
                }
            }
        }

        BottomSheetScaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = MaterialTheme.colorScheme.surface,
            sheetContent = {
                ScrollableText(
                    modifier = Modifier
                        .padding(horizontal = localDimensions.horizontalPreLarge)
                        .fillMaxSize(),
                    text = state.translatedText,
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
                    bitmap = state.image.asImageBitmap(),
                    contentDescription = null
                )
                RecognizedLetter(
                    modifier = Modifier
                        .width(48.dp)
                        .padding(bottom = 40.dp),
                    letter = state.recognizedLetter
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
                            (constraints.maxWidth - sumWidhtPlaceables) / max(
                                placeables.size,
                                2
                            )
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
                    checked = translateChecked,
                    text = stringResource(id = R.string.recognition_button),
                    onClick = {
                        coroutineScope.launch {
                            translateChecked = it
                            if (it) {
                                scaffoldState.bottomSheetState.partialExpand()
                            } else {
                                scaffoldState.bottomSheetState.expand()
                            }
                            if (permissionGranted) viewModel.onTranslatingStatusChanged(it)
                        }
                    }
                )
                AnimatedVisibility(
                    visible = isBehaviorOnExpanded,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    RadioButton(
                        modifier = Modifier.padding(horizontal = localDimensions.horizontalTiny),
                        checked = correctChecked && isBehaviorOnExpanded,
                        text = stringResource(id = R.string.auto_correct_button),
                        onClick = {
                            correctChecked = it
                            viewModel.onTextCorrectedStatusChanged(it)
                        }
                    )
                }
            }
        )
    }
}
