package com.jjrodcast.data.mappers

import com.jjrodcast.data.entities.NoteEntity
import com.jjrodcast.domain.entities.Note

class NoteDataMapper {

    fun map(noteEntity: NoteEntity) = with(noteEntity) {
        Note(
            id = id,
            title = title,
            description = description,
            color = color,
            date = date
        )
    }

    fun map(note: Note) = with(note) {
        NoteEntity(
            id = id,
            title = title,
            description = description,
            color = color,
            date = date
        )
    }
}