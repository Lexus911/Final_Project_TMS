package com.example.teachmenotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.teachmenotes.databinding.FragmentNotesDetailsBinding
import com.example.teachmenotes.utils.BundleConstants.ID
import com.example.teachmenotes.utils.BundleConstants.NOTE
import com.example.teachmenotes.utils.BundleConstants.TITLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesDetailsFragment : Fragment() {
    private var _binding: FragmentNotesDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let { safeBundle ->
            val title = safeBundle.getString(TITLE)
            val note = safeBundle.getString(NOTE)

            binding.editTextTitle.setText(title)
            binding.editTextNote.setText(note)
        }

        binding.imageViewSaveEditNote.setOnClickListener {
            if (bundle != null) {
                viewModel.saveEditNote(
                    binding.editTextTitle.text.toString(),
                    binding.editTextNote.text.toString(),
                    bundle.getInt(ID)
                )
            }
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(it)
            }
        }
    }
}