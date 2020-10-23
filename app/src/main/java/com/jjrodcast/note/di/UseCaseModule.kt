package com.jjrodcast.note.di

import com.jjrodcast.domain.repositories.NoteRepository
import com.jjrodcast.domain.usecases.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent


@InstallIn(ActivityComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideNoteUseCase(noteRepository: NoteRepository): NoteUseCase {
        return NoteUseCase(noteRepository)
    }
}
