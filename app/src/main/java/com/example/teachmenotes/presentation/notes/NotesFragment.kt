package com.example.teachmenotes.presentation.notes

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.FragmentNotesBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.NotesAdapter
import com.example.teachmenotes.presentation.notes.adapter.listener.NotesListener
import com.example.teachmenotes.utils.BundleConstants.COLOR
import com.example.teachmenotes.utils.BundleConstants.COLOR_VALUE
import com.example.teachmenotes.utils.BundleConstants.ID
import com.example.teachmenotes.utils.BundleConstants.NOTE
import com.example.teachmenotes.utils.BundleConstants.TITLE
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
        binding.recyclerViewNotes.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recyclerViewNotes.adapter = notesAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.notes.catch {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
                .collect { flowList ->
                    flowList.collect { listNotes ->
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
                viewModel.navFinished()
            }
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->

            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putInt(ID, navBundle.id)
                bundle.putString(TITLE, navBundle.title)
                bundle.putString(NOTE, navBundle.note)
                bundle.putString(COLOR, navBundle.color)

               findNavController().navigate(navBundle.destinationId, bundle)
                viewModel.userNavigated()
            }
        }
    }


    override fun onClick(noteModel: NoteModel) {
        viewModel.noteClicked(noteModel.id!!, noteModel.title, noteModel.note, noteModel.color)
    }

    override fun onLongClick(noteModel: NoteModel, cardView: CardView) {
        val popupMenu = PopupMenu(requireContext(), cardView)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_note -> {
                viewModel.deleteNote(noteModel.id!!)
                }
            }
            false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }
}


