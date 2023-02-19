package com.example.teachmenotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.teachmenotes.databinding.FragmentAddNoteBinding
import com.example.teachmenotes.presentation.model.NoteModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddNoteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))


        //Добавить проверку на пустые поля
        binding.imageViewSaveNote.setOnClickListener {
            viewModel.saveNote(
                NoteModel(
                    null,
                    binding.editTextTitle.text.toString(),
                    binding.editTextNote.text.toString(),
                    date,
                    false
                )
            )
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(it)
            }
    }
    }
}