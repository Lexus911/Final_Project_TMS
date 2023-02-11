package com.example.teachmenotes.presentation.tasks

import androidx.lifecycle.ViewModel
import com.example.teachmenotes.domain.NotesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
): ViewModel() {


}