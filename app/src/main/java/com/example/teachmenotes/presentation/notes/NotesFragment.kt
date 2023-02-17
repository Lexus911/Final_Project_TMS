package com.example.teachmenotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachmenotes.databinding.FragmentNotesBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.NotesAdapter
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch

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

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.notes.catch {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
                .collect{ flowList ->
                    flowList.collect{ listNotes ->
                        notesAdapter.submitList(listNotes)
                    }
                }
        }


        binding.btnAddNote.setOnClickListener {
            viewModel.addNoteButtonClicked()
            }
        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(it)
            }
        }
    }

    override fun onClick(noteModel: NoteModel) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(noteModel: NoteModel, cardView: CardView) {
        TODO("Not yet implemented")
    }




}