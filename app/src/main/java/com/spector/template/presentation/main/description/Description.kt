package com.spector.template.presentation.main.description

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface State
interface SideEffect

interface Store<S : State, E : SideEffect> {
    fun observeState(): StateFlow<S>
    fun observeSideEffect(): SharedFlow<E>
}