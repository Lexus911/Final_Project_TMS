package com.example.teachmenotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.FragmentAddNoteBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.ColorsAdapter
import com.example.teachmenotes.presentation.notes.adapter.listener.ColorsListener
import com.example.teachmenotes.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class AddNoteFragment : Fragment(), ColorsListener {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var colorsAdapter: ColorsAdapter
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

        colorsAdapter = ColorsAdapter(this)
        binding.recyclerViewColors.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerViewColors.adapter = colorsAdapter

        viewModel.getColors()

        viewModel.colors.observe(viewLifecycleOwner){
            colorsAdapter.submitList(it)
        }

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))

        val bundle = arguments
        bundle?.let { safeBundle ->
            val title = safeBundle.getString(BundleConstants.TITLE)
            val note = safeBundle.getString(BundleConstants.NOTE)
            binding.editTextTitle.setText(title)
            binding.editTextNote.setText(note)
        }

        //Добавить проверку на пустые поля
        binding.imageViewSaveNote.setOnClickListener {
            if(bundle != null){
                viewModel.saveEditNote(
                    binding.editTextTitle.text.toString(),
                    binding.editTextNote.text.toString(),
                    bundle.getInt(BundleConstants.ID),
                    "color"
                )
            }else{
                viewModel.saveNote(
                    NoteModel(
                        null,
                        binding.editTextTitle.text.toString(),
                        binding.editTextNote.text.toString(),
                        date,
                        "color"
                    )
                )
            }
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(it)
            }
    }

        binding.imageViewShowRecyclerView.setOnClickListener {
            if(binding.recyclerViewColors.visibility == GONE){
                binding.recyclerViewColors.visibility = VISIBLE
            }else{
                binding.recyclerViewColors.visibility = GONE
            }
        }



    }

    override fun onClick() {
        TODO("Not yet implemented")
    }
}