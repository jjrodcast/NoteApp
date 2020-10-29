package com.jjrodcast.note.mvi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel

// MVI - Interfaces

// Interface to manage the UI-State (it's part of the UI)
interface State

// Interface to manage the View with its State
interface View<S : State> {
    fun render(state: S)
}

// Interface to manage Intents (we say Intent to a User Intention that wants to execute)
interface Intent

// Interface to manage Model (we say Model to a class that expose State and Intent)
interface Model<S : State, I : Intent> {
    val intent: Channel<I>
    val state: LiveData<S>
}

// MviViewModel is an abstract class that handles the state of your ViewModel
abstract class MviViewModel<S : State, I : Intent> : ViewModel(), Model<S, I> {

    override val intent: Channel<I> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<S>()
    override val state: LiveData<S>
        get() = _state

    init {
        this.manageIntents()
    }

    abstract fun manageIntents()

    protected suspend fun updateState(isMain: Boolean = false, resolver: suspend (S?) -> S) {
        Log.d("MviViewModel::updateState", "${state.value}")
        if (isMain) _state.value = resolver(state.value)
        else _state.postValue(resolver(state.value))
    }
}