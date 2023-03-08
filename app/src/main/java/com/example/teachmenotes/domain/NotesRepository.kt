package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun saveNote(noteModel: NoteModel)

    suspend fun showNotes(): Flow<List<NoteModel>>

    suspend fun deleteNoteById(id: Int)

    suspend fun saveEditNote(title: String, note: String, id: Int)

    suspend fun getColors(): List<ColorModel>

    suspend fun colorSelected(color: String, id: Int)

    suspend fun listLayoutPressed()

    suspend fun gridLayoutPressed()

    suspend fun checkLayout(): Boolean

    suspend fun sortingByDateDESC()

    suspend fun sortingByDateASC()

    suspend fun checkSortType(): Boolean



}