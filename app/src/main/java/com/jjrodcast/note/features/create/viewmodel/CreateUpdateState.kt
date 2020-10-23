package com.jjrodcast.note.features.create.viewmodel

import com.jjrodcast.domain.entities.Note

sealed class CreateUpdateState {
    class SearchResponse(val note: Note) : CreateUpdateState()
    object ModelNotValid : CreateUpdateState()
    object NotFound : CreateUpdateState()
    object Done : CreateUpdateState()
    object Failure : CreateUpdateState()
}