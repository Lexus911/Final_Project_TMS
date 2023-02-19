package com.example.teachmenotes.presentation.notes.adapter.listener

import androidx.cardview.widget.CardView
import com.example.teachmenotes.presentation.model.NoteModel

interface NotesListener {

    fun onClick(noteModel: NoteModel)

    fun onLongClick(noteModel: NoteModel, cardView: CardView)

}