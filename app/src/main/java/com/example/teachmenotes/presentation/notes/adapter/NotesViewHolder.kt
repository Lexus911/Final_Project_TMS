package com.example.teachmenotes.presentation.notes.adapter

import android.graphics.Color
import android.graphics.Color.RED
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.NotesListBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener
import com.example.teachmenotes.utils.BundleConstants.ID_NOTES_VIEW_HOLDER

class NotesViewHolder(private val binding: NotesListBinding,
                      private var notesListener: NotesListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(noteModel: NoteModel){

        binding.tvTitle.text = noteModel.title
        binding.tvNote.text = noteModel.note
        binding.tvDate.text = noteModel.date

        binding.notesContainer.setCardBackgroundColor(Color.parseColor(noteModel.color))

        binding.notesContainer.setOnClickListener {
            notesListener.onClick(noteModel)
            val bundle = Bundle()
            bundle.putInt(ID_NOTES_VIEW_HOLDER, noteModel.id!!)
        }

        binding.notesContainer.setOnLongClickListener{
            notesListener.onLongClick(noteModel, binding.notesContainer)
            true
        }
    }


}