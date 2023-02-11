package com.example.teachmenotes.presentation.notes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.NotesListBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener

class NotesViewHolder(private val binding: NotesListBinding,
                      private var notesListener: NotesListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(noteModel: NoteModel){



        if (binding.notesContainer.isSelected){
            binding.ivPin.setBackgroundResource(R.drawable.pin_icon)
        }else{
            binding.ivPin.setBackgroundResource(0)
        }
    }


}