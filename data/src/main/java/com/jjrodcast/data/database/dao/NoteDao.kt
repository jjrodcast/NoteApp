package com.jjrodcast.data.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.jjrodcast.data.entities.NoteEntity
import com.jjrodcast.domain.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveNote(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = :noteId")
    fun getNoteById(noteId: Long): Flow<NoteEntity>

    @Update(onConflict = REPLACE, entity = NoteEntity::class)
    suspend fun updateNote(note: NoteEntity)

    @Delete(entity = NoteEntity::class)
    suspend fun delete(note: NoteEntity)
}