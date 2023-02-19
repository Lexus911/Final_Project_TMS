package com.example.teachmenotes.di

import android.content.Context
import com.example.teachmenotes.data.database.dao.NotesDAO
import com.example.teachmenotes.data.database.dao.NotesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent:: class)
class DataBaseModule {

    @Provides
    fun notesDataBase(context: Context): NotesDataBase {
        return NotesDataBase.getNotesDataBaseInstance(context)
    }

    @Provides
    fun provideNotesDao(notesDataBase: NotesDataBase): NotesDAO {
        return notesDataBase.getNotesDAO()
    }
}