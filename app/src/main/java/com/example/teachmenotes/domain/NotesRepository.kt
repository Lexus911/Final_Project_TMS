package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.NoteModel

interface NotesRepository {
    fun getData():List<NoteModel>
}