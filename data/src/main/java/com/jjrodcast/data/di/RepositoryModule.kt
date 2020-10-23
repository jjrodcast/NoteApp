package com.jjrodcast.data.di

import com.jjrodcast.data.repositories.NoteDataRepository
import com.jjrodcast.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNoteRepository(noteDataRepository: NoteDataRepository): NoteRepository
}