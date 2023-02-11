package com.example.teachmenotes.presentation.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.NotesListBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener

class NotesAdapter(private var notesListener: NotesListener): RecyclerView.Adapter<NotesViewHolder>() {
    private var listNotes = listOf<NoteModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<NoteModel>){

        this.listNotes = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding, notesListener)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }
}