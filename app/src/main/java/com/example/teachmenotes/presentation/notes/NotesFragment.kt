package com.example.teachmenotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachmenotes.databinding.FragmentNotesBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.NotesAdapter
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(), NotesListener {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesAdapter: NotesAdapter
    private val viewModel: NotesViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesAdapter = NotesAdapter(this)
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewNotes.adapter = notesAdapter

        viewModel.getData()

        viewModel.notes.observe(viewLifecycleOwner){ listNotes ->
            notesAdapter.submitList(listNotes)
        }


    }

    override fun onClick(noteModel: NoteModel) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(noteModel: NoteModel, cardView: CardView) {
        TODO("Not yet implemented")
    }


}