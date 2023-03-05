package com.example.teachmenotes.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teachmenotes.data.database.NotesEntity
import com.example.teachmenotes.data.database.TasksEntity

@Database(entities = [NotesEntity::class, TasksEntity::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {

    abstract fun getNotesDAO(): NotesDAO

    companion object{
        private const val DATABASE_NAME = "notes_db"
        private var DATABASE_INSTANCE: NotesDataBase? = null

        fun getNotesDataBaseInstance(context: Context): NotesDataBase {
            return DATABASE_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    NotesDataBase::class.java,
                    DATABASE_NAME
                ).build()
                .also{ DATABASE_INSTANCE = it }
        }
    }
}