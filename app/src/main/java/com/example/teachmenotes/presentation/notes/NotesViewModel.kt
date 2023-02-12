package com.example.teachmenotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teachmenotes.R
import com.example.teachmenotes.domain.NotesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
    ):ViewModel() {



}