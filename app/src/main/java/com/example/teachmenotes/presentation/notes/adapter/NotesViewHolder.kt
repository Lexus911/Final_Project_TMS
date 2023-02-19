package com.example.teachmenotes.presentation.notes.adapter

import android.graphics.Color
import android.graphics.Color.RED
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.NotesListBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener

class NotesViewHolder(private val binding: NotesListBinding,
                      private var notesListener: NotesListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(noteModel: NoteModel){

        binding.tvTitle.text = noteModel.title
        binding.tvNote.text = noteModel.note
        binding.tvDate.text = noteModel.date

        binding.notesContainer.setOnClickListener {
            notesListener.onClick(noteModel)
        }

        binding.notesContainer.setOnLongClickListener{
            notesListener.onLongClick(noteModel, binding.notesContainer)
            true
        }

    }


}