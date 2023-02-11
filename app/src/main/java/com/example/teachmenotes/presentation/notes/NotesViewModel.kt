package com.example.teachmenotes.presentation.notes

import androidx.lifecycle.ViewModel
import com.example.teachmenotes.domain.NotesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
    ):ViewModel() {


}