package com.example.teachmenotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachmenotes.R
import com.example.teachmenotes.domain.NotesInteractor
import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
): ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _colors = MutableLiveData <List<ColorModel>>()
    val colors: LiveData<List<ColorModel>> = _colors


    fun colorClicked(){

    }

    fun getColors() {
       viewModelScope.launch {
            try {
               _colors.value = notesInteractor.getColors()
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }
    }

    fun saveNote(noteModel: NoteModel) {
        viewModelScope.launch {
            try {
                notesInteractor.saveNote(noteModel)
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }
        _nav.value = R.id.action_addNoteFragment_to_notesFragment
    }

    fun saveEditNote(title: String, note: String, id: Int, color: String) {
        viewModelScope.launch {
            try {
                notesInteractor.saveEditNote(title, note, id, color)
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }
        _nav.value = R.id.action_addNoteFragment_to_notesFragment
    }


}