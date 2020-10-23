package com.jjrodcast.data.repositories

import com.jjrodcast.data.datasources.NoteDataSource
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

class NoteDataRepository @Inject constructor(
    private val noteDataSource: NoteDataSource
) : NoteRepository {

    override fun getNoteById(noteId: Long): Flow<Note> {
        return noteDataSource.getNoteById(noteId)
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDataSource.getNotes().conflate()
    }

    override suspend fun insertNote(note: Note) {
        noteDataSource.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDataSource.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDataSource.deleteNote(note)
    }
}