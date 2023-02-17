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
}