package com.example.teachmenotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teachmenotes.domain.NotesInteractor
import com.example.teachmenotes.presentation.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
    ):ViewModel() {

    private val _notes = MutableLiveData<List<NoteModel>>()
    val notes: LiveData<List<NoteModel>> = _notes

    fun getData() {
        val listNotes = notesInteractor.getData()
        _notes.value = listNotes
    }

}