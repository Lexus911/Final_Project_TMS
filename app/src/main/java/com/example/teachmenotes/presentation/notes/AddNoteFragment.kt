package com.example.teachmenotes.presentation.notes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.FragmentAddNoteBinding
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.ColorsAdapter
import com.example.teachmenotes.presentation.notes.adapter.listener.ColorsListener
import com.example.teachmenotes.utils.BundleConstants.COLOR
import com.example.teachmenotes.utils.BundleConstants.ID
import com.example.teachmenotes.utils.BundleConstants.ID_NOTES_VIEW_HOLDER
import com.example.teachmenotes.utils.BundleConstants.NOTE
import com.example.teachmenotes.utils.BundleConstants.TITLE
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

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorsAdapter = ColorsAdapter(this)
        binding.recyclerViewColors.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerViewColors.adapter = colorsAdapter

        viewModel.getColors()

        viewModel.colors.observe(viewLifecycleOwner) {
            colorsAdapter.submitList(it)
        }

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))

        val bundle = arguments
        bundle?.let { safeBundle ->
            val title = safeBundle.getString(TITLE)
            val note = safeBundle.getString(NOTE)
            val color = safeBundle.getString(COLOR)

            binding.editTextTitle.setText(title)
            binding.editTextNote.setText(note)
            binding.linearLayout.setBackgroundColor(Color.parseColor(color))
        }

        binding.imageViewSaveNote.setOnClickListener {
            if (bundle != null) {
                viewModel.saveEditNote(
                    binding.editTextTitle.text.toString(),
                    binding.editTextNote.text.toString(),
                    bundle.getInt(ID),
                )
            } else {
                viewModel.saveNote(
                    NoteModel(
                        null,
                        binding.editTextTitle.text.toString(),
                        binding.editTextNote.text.toString(),
                        date,
                        if(binding.colorTextView.text.isNotEmpty()){
                            binding.colorTextView.text.toString()
                        }else{getString(R.string.default_color)}
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
            val hideKeyboard = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKeyboard.hideSoftInputFromWindow(binding.imageViewShowRecyclerView.windowToken, 0)

            viewModel.visibilityRecyclerView()

            viewModel.visibility.observe(viewLifecycleOwner) {
                binding.recyclerViewColors.visibility = it
            }
        }
    }


    override fun onClick(color: String) {

        binding.linearLayout.setBackgroundColor(Color.parseColor(color))

        binding.colorTextView.text = color

        val bundle = arguments
        bundle?.let { safeBundle ->
            val id = safeBundle.getInt(ID_NOTES_VIEW_HOLDER)

            viewModel.colorSelected(color, id)
        }
    }
}