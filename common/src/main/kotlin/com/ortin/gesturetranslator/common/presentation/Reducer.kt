package com.ortin.gesturetranslator.common.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base class for Reducer in MVI
 *
 * Responsible for holding and processing state
 *
 * @param S - state
 * @param I - intent
 * @property initialValue - value for initialize state
 */
abstract class Reducer<S: UiState, I: ModelIntent>(private val initialValue: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialValue)
    val state: StateFlow<S> get() = _state.asStateFlow()

    /**
     * Method for generating new state based on [intent]
     */
    fun sendIntent(intent: I){
        reduce(_state.value, intent)
    }

    /**
     * Method for setting [newState] into current state flow
     */
    fun setState(newState: S){
        _state.tryEmit(newState)
    }

    /**
     * Method, which takes [oldState], [intent] and process them
     */
    abstract fun reduce(oldState: S, intent: I)
}
