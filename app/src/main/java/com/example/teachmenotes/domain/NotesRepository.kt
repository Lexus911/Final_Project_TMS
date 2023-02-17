package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun saveNote(noteModel: NoteModel)

    suspend fun showNotes(): Flow<List<NoteModel>>
}