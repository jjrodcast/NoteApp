package com.jjrodcast.domain.repositories

import com.jjrodcast.domain.entities.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNoteById(noteId: Long): Flow<Note>
    fun getNotes(): Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}