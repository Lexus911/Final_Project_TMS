package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.model.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesInteractor @Inject constructor(private val notesRepository: NotesRepository) {

    suspend fun saveNote(noteModel: NoteModel){
        return notesRepository.saveNote(noteModel)
    }

    suspend fun showNotes(): Flow<List<NoteModel>> {
        return notesRepository.showNotes()
    }

    suspend fun deleteNoteById(id: Int){
        notesRepository.deleteNoteById(id)
    }

    suspend fun saveEditNote(title: String, note: String, id: Int, color: String){
        return notesRepository.saveEditNote(title, note, id, color)
    }

    suspend fun getColors() : List<ColorModel>{
        return notesRepository.getColors()
    }
}