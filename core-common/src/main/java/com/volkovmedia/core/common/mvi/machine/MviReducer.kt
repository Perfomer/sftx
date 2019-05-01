package com.volkovmedia.core.common.mvi.machine

abstract class MviReducer<State : Any, Action : Any> {

    open fun reduce(oldState: State, action: Action): State = oldState

}