package com.example.teachmenotes.data

import com.example.teachmenotes.data.database.NotesEntity
import com.example.teachmenotes.data.database.dao.NotesDAO
import com.example.teachmenotes.data.service.ApiService
import com.example.teachmenotes.data.shared_preferences.SharedPreferencesHelper
import com.example.teachmenotes.domain.NotesRepository
import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val apiService: ApiService,
    private val notesDAO: NotesDAO,
    ) : NotesRepository{

    override suspend fun saveNote(noteModel: NoteModel) {
        withContext(Dispatchers.IO) {
            notesDAO.insertNotesEntity(
                NotesEntity(
                    noteModel.id,
                    noteModel.title,
                    noteModel.note,
                    noteModel.date,
                    noteModel.color
                )
            )
        }
    }

    override suspend fun showNotes(): Flow<List<NoteModel>> {
            return withContext(Dispatchers.IO) {

               val notesEntity = when(sharedPreferencesHelper.checkSortType()){
                    true -> notesDAO.getNotesEntitiesDESC()
                    false -> notesDAO.getNotesEntitiesASC()
                }
                notesEntity.map {notesList ->
                    notesList.map{
                    NoteModel(
                        it.id,
                        it.title,
                        it.note,
                        it.date,
                        it.color
                    )
                }
            }
        }
    }

    override suspend fun deleteNoteById(id: Int) {
        withContext(Dispatchers.IO) {
            notesDAO.deleteNoteEntityById(id)
        }
    }

    override suspend fun saveEditNote(title: String, note: String, id: Int) {
        withContext(Dispatchers.IO) {
            notesDAO.saveEditNote(title, note, id)
        }
    }

    override suspend fun getColors(): List<ColorModel> {
        return withContext(Dispatchers.IO){
            val response = apiService.getColors()
            response.body()?.colorsList?.let{
                it.map {it ->
                    ColorModel(it.value)
                }
            } ?: kotlin.run{
                emptyList()
            }
        }
    }

    override suspend fun colorSelected(color: String, id: Int) {
        withContext(Dispatchers.IO) {
            notesDAO.colorSelected(color, id)
        }
    }

    override suspend fun listLayoutPressed() {
        withContext(Dispatchers.IO) { sharedPreferencesHelper.listLayoutPressed() }
    }

    override suspend fun gridLayoutPressed() {
        withContext(Dispatchers.IO) { sharedPreferencesHelper.gridLayoutPressed() }
    }

    override suspend fun checkLayout(): Boolean {
        return withContext(Dispatchers.IO) { sharedPreferencesHelper.checkLayout() }
    }

    override suspend fun sortingByDateDESC() {
        withContext(Dispatchers.IO) { sharedPreferencesHelper.sortingByDateDESC() }
    }

    override suspend fun sortingByDateASC() {
        withContext(Dispatchers.IO) { sharedPreferencesHelper.sortingByDateASC() }
    }

    override suspend fun checkSortType(): Boolean {
        return withContext(Dispatchers.IO) { sharedPreferencesHelper.checkSortType() }
    }
}