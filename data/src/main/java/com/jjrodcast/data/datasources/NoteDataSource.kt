package com.jjrodcast.data.datasources

import com.jjrodcast.data.database.dao.NoteDao
import com.jjrodcast.data.mappers.NoteDataMapper
import com.jjrodcast.domain.entities.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteDataSource @Inject constructor(
    private val noteDao: NoteDao,
    private val noteDataMapper: NoteDataMapper
) {

    fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { notes -> notes.map { noteDataMapper.map(it) } }
    }

    suspend fun insertNote(note: Note) {
        noteDao.saveNote(noteDataMapper.map(note))
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(noteDataMapper.map(note))
    }

    fun getNoteById(noteId: Long): Flow<Note> {
        return noteDao.getNoteById(noteId).map { noteDataMapper.map(it) }
    }

    suspend fun deleteNote(note: Note) {
        return noteDao.delete(noteDataMapper.map(note))
    }
}