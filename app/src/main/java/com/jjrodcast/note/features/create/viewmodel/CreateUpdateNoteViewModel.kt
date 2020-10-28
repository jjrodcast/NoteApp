package com.jjrodcast.note.features.create.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.usecases.NoteUseCase
import com.jjrodcast.note.utils.asLiveData
import com.jjrodcast.note.utils.io
import com.jjrodcast.note.utils.isNew
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateUpdateNoteViewModel @ViewModelInject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CreateUpdateState>()
    val state get() = _state.asLiveData()

    fun insertOrUpdateNote(note: Note) {
        if (!note.isValid()) _state.value = CreateUpdateState.ModelNotValid
        else if (!note.id.isNew()) doUpdate(note)
        else doSave(note)
    }

    fun getNote(noteId: Long) {
        if (noteId.isNew()) _state.value = CreateUpdateState.NotFound
        else doSearch(noteId)
    }

    private fun doSave(note: Note) {
        viewModelScope.launch(handler) {
            io { noteUseCase.insertNote(note) }
            _state.value = CreateUpdateState.Done
        }
    }

    private fun doUpdate(note: Note) {
        viewModelScope.launch(handler) {
            io { noteUseCase.updateNote(note) }
            _state.value = CreateUpdateState.Done
        }
    }

    private fun doSearch(noteId: Long) {
        viewModelScope.launch(handler) {
            io {
                noteUseCase.getNoteById(noteId)
                    .collect { note ->
                        _state.postValue(CreateUpdateState.SearchResponse(note))
                    }
            }
        }
    }

    private val handler = CoroutineExceptionHandler { _, _ ->
        _state.value = CreateUpdateState.Failure
    }

}