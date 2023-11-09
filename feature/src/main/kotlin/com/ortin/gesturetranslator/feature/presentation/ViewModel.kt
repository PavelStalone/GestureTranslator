package com.ortin.gesturetranslator.feature.presentation

import kotlinx.coroutines.flow.Flow

/**
 * Base class for ViewModel in MVI
 *
 * Accepts intent and works on its basis with the state
 *
 * @param S - state
 * @param I - intent
 */
abstract class ViewModel<S: UiState, in I: ModelIntent> {
    abstract val state: Flow<S>
}
