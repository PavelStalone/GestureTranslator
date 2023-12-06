package com.ortin.gesturetranslator.common.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

/**
 * Base class for ViewModel in MVI
 *
 * Accepts intent and works on its basis with the state
 *
 * @param S - state
 * @param I - intent
 */
abstract class ViewModel<S: UiState, in I: ModelIntent>: ViewModel() {

    abstract val state: Flow<S>
}
