package com.jjrodcast.data.di

import com.jjrodcast.data.database.dao.NoteDao
import com.jjrodcast.data.datasources.NoteDataSource
import com.jjrodcast.data.mappers.NoteDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DataSourceModule {

    @Provides
    fun provideNoteDataMapper(): NoteDataMapper {
        return NoteDataMapper()
    }

    @Provides
    fun provideNoteDataSource(noteDao: NoteDao, noteDataMapper: NoteDataMapper): NoteDataSource {
        return NoteDataSource(noteDao, noteDataMapper)
    }
}