package com.ortin.gesturetranslator.domain.managers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class FlowManager<T>(initialValue: T) {
    protected val _flow: MutableStateFlow<T> = MutableStateFlow(initialValue)
    val flow: StateFlow<T>
        get() = _flow.asStateFlow()
}
