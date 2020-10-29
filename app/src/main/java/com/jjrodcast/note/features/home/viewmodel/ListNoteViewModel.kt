package com.jjrodcast.note.features.home.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.usecases.NoteUseCase
import com.jjrodcast.note.features.home.states.NoteListIntent
import com.jjrodcast.note.features.home.states.NoteListState
import com.jjrodcast.note.mvi.MviViewModel
import com.jjrodcast.note.utils.io
import com.jjrodcast.note.utils.ui
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListNoteViewModel @ViewModelInject constructor(
    private val noteUseCase: NoteUseCase
) : MviViewModel<NoteListState, NoteListIntent>() {

    override fun manageIntents() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect { noteIntent ->
                when (noteIntent) {
                    is NoteListIntent.DeleteNote -> deleteNote(noteIntent.note)
                    NoteListIntent.FetchNotes -> fetchNotes()
                }
            }
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch(handler) {
            io {
                updateState { NoteListState.Loading }
                collectNotes()
            }
        }
    }

    private suspend fun collectNotes() {
        noteUseCase.getNotes()
            .map { notes -> NoteListState.Success(notes) }
            .collect { state -> updateState { state } }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch(handler) {
            io {
                updateState {
                    noteUseCase.deleteNote(note)
                    NoteListState.Done
                }
            }
        }
    }

    private val handler = CoroutineExceptionHandler { _, e ->
        ui {
            updateState(isMain = true) { NoteListState.Failure }
            Log.d("Exception", e.message.toString())
        }
    }
}