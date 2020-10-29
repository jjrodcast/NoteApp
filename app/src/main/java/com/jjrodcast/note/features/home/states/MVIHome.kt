package com.jjrodcast.note.features.home.states

import com.jjrodcast.domain.entities.Note
import com.jjrodcast.note.mvi.Intent
import com.jjrodcast.note.mvi.State

sealed class NoteListState : State {
    class Success(val notes: List<Note>) : NoteListState()
    object Loading : NoteListState()
    object Done : NoteListState()
    object Failure : NoteListState()
}

sealed class NoteListIntent : Intent {
    object FetchNotes : NoteListIntent()
    class DeleteNote(val note: Note) : NoteListIntent()
}
