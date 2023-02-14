package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.NoteModel
import javax.inject.Inject

class NotesInteractor @Inject constructor(private val notesRepository: NotesRepository) {
    fun getData(): List<NoteModel> {
        return notesRepository.getData()
    }
}