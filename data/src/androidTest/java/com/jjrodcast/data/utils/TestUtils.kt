package com.jjrodcast.data.utils

import com.jjrodcast.data.entities.NoteEntity
import com.jjrodcast.domain.entities.Note
import java.util.*

const val ZERO = 0L

object TestUtils {

    val noteEntity =
        NoteEntity(
            id = 1,
            title = "title",
            description = "description",
            color = 101010,
            date = Date().time
        )

    val note = Note(
        id = 1,
        title = "Note 1",
        description = "Description 1",
        color = 10,
        date = Date().time
    )

    const val randomTitle = "random_title"
}