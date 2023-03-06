package com.example.teachmenotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachmenotes.R
import com.example.teachmenotes.domain.NotesInteractor
import com.example.teachmenotes.presentation.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
    ):ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    val notes = flow<Flow<List<NoteModel>>>{ emit(notesInteractor.showNotes()) }

    fun addNoteButtonClicked(){
        _nav.value = R.id.action_viewPagerFragment_to_addNoteFragment
    }

    fun noteClicked(id: Int, title: String, note: String, color: String){
        _bundle.value = NavigateWithBundle(id, title, note, destinationId = R.id.action_viewPagerFragment_to_addNoteFragment, color)
    }

    fun userNavigated(){
        _bundle.value = null

    }
    fun navFinished(){
        _nav.value = null
    }


    fun deleteNote(id: Int) {
        viewModelScope.launch {
            try {
                notesInteractor.deleteNoteById(id)
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }

    }
}

data class NavigateWithBundle(
    val id: Int,
    val title: String,
    val note: String,
    val destinationId: Int,
    val color: String,
)