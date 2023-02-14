package com.example.teachmenotes.data

import com.example.teachmenotes.domain.NotesRepository
import com.example.teachmenotes.presentation.model.NoteModel
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor() : NotesRepository{
    override fun getData(): List<NoteModel> {
        return listOf<NoteModel>(
            NoteModel(1,"buy list","milk","20.01.2021",false),
            NoteModel(2,"buy list","water","20.02.2021",false),
            NoteModel(3,"buy list","salt","20.03.2021",false),
            NoteModel(4,"buy list","sugar","20.04.2021",false),
            NoteModel(5,"buy list","chocolate","20.05.2021",false),
            NoteModel(1,"buy list","milk","20.01.2021",false),
            NoteModel(2,"buy list","water","20.02.2021",false),
            NoteModel(3,"buy list","salt","20.03.2021",false),
            NoteModel(4,"buy list","sugar","20.04.2021",false),

        )
    }

}