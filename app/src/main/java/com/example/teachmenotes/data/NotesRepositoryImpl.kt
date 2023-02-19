package com.example.teachmenotes.data

import com.example.teachmenotes.data.database.NotesEntity
import com.example.teachmenotes.data.database.dao.NotesDAO
import com.example.teachmenotes.data.service.ApiService
import com.example.teachmenotes.domain.NotesRepository
import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val notesDAO: NotesDAO
    ) : NotesRepository{

    override suspend fun saveNote(noteModel: NoteModel) {
        withContext(Dispatchers.IO) {
            notesDAO.insertNotesEntity(
                NotesEntity(
                    noteModel.id,
                    noteModel.title,
                    noteModel.note,
                    noteModel.date,
                    noteModel.pinned
                )
            )
        }
    }

    override suspend fun showNotes(): Flow<List<NoteModel>> {
            return withContext(Dispatchers.IO) {
                val notesEntity = notesDAO.getNotesEntities()
                notesEntity.map {notesList ->
                    notesList.map{
                    NoteModel(
                        it.id,
                        it.title,
                        it.note,
                        it.date,
                        it.pinned
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

    override suspend fun getColors() {
        return withContext(Dispatchers.IO){
            val response = apiService.getColors()
            response.body()?.colorsList?.let{
                it.map {
                    ColorModel(it.name, it.value)
                }
            } ?: kotlin.run{
                emptyList()
            }
        }
    }
}