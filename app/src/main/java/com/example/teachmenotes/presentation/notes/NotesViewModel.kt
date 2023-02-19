package com.example.teachmenotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teachmenotes.R
import com.example.teachmenotes.domain.NotesInteractor
import com.example.teachmenotes.presentation.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
    ):ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    val notes = flow<Flow<List<NoteModel>>>{ emit(notesInteractor.showNotes()) }

    fun addNoteButtonClicked(){
        _nav.value = R.id.action_notesFragment_to_addNoteFragment
    }

    fun noteClicked(id: Int, title: String, note: String){
        _bundle.value = NavigateWithBundle(id, title, note, destinationId = R.id.action_notesFragment_to_notesDetailsFragment)
    }

    fun userNavigated(){
        _bundle.value = null
    }

}

data class NavigateWithBundle(
    val id: Int,
    val title: String,
    val note: String,
    val destinationId: Int
)