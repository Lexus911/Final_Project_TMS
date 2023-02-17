package com.example.teachmenotes.data

import android.util.Log
import com.example.teachmenotes.data.database.NotesEntity
import com.example.teachmenotes.data.database.dao.NotesDAO
import com.example.teachmenotes.domain.NotesRepository
import com.example.teachmenotes.presentation.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
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
            Log.w("ADD TO DB","Note added to DB")
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


}