package com.example.teachmenotes.di

import com.example.teachmenotes.domain.NotesInteractor
import com.example.teachmenotes.domain.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideNotesInteractor(notesRepository: NotesRepository): NotesInteractor {
        return NotesInteractor(notesRepository)
    }

}
