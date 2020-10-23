package com.jjrodcast.domain.usecases

import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    fun getNotes(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }

    fun getNoteById(noteId: Long): Flow<Note> {
        return noteRepository.getNoteById(noteId)
    }

    suspend fun insertNote(note: Note) {
        noteRepository.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }
}