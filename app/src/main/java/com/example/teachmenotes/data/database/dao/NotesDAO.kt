package com.example.teachmenotes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.teachmenotes.data.database.NotesEntity
import com.example.teachmenotes.data.database.TasksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Insert(onConflict = IGNORE)
    fun insertNotesEntity(notesEntity: NotesEntity)

    @Query("SELECT * From NotesEntity ORDER BY date DESC, id DESC")
    fun getNotesEntitiesDESC(): Flow<List<NotesEntity>>

    @Query("SELECT * From NotesEntity ORDER BY date ASC, id DESC")
    fun getNotesEntitiesASC(): Flow<List<NotesEntity>>

    @Query("DELETE FROM NotesEntity WHERE id =:id")
    fun deleteNoteEntityById(id: Int)

    @Query("UPDATE NotesEntity SET title =:title, note =:note WHERE id =:id")
    fun saveEditNote(title: String, note: String, id: Int)

    @Query("UPDATE NotesEntity SET color =:color WHERE id =:id")
    fun colorSelected(color: String, id: Int)


    @Insert
    fun insertTasksEntity(tasksEntity: TasksEntity)

    @Query("SELECT * From TasksEntity ORDER BY completed ASC, id DESC")
    fun getTasksEntities(): Flow<List<TasksEntity>>

    @Query("DELETE FROM TasksEntity WHERE id =:id")
    fun deleteTaskEntityById(id: Int)

    @Query("UPDATE TasksEntity SET task =:task WHERE id =:id")
    fun saveEditTask(task: String, id: Int)

    @Query("UPDATE TasksEntity SET completed =:completed WHERE id =:id")
    fun setCompleted(completed: Boolean, id: Int)
}