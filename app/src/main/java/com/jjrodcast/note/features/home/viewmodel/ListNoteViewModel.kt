package com.jjrodcast.note.features.home.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.usecases.NoteUseCase
import com.jjrodcast.note.utils.io
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ListNoteViewModel @ViewModelInject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    val notes: LiveData<List<Note>>
        get() = noteUseCase.getNotes().asLiveData()


    fun deleteNote(note: Note) {
        viewModelScope.launch(handler) {
            io { noteUseCase.deleteNote(note) }
        }
    }


    private val handler = CoroutineExceptionHandler { _, e ->
        Log.d("Error", e.message.toString())
    }

}