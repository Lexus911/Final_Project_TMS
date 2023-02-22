package com.example.teachmenotes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.teachmenotes.data.database.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Insert
    fun insertNotesEntity(notesEntity: NotesEntity)

    @Query("SELECT * From NotesEntity")
    fun getNotesEntities(): Flow<List<NotesEntity>>

    @Query("DELETE FROM NotesEntity WHERE id =:id")
    fun deleteNoteEntityById(id: Int)

    @Query("UPDATE NotesEntity SET title =:title, note =:note, color =:color WHERE id =:id")
    fun saveEditNote(title: String, note: String, id: Int, color: String)
}