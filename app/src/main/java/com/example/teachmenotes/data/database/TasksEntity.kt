package com.example.teachmenotes.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("TasksEntity")
data class TasksEntity (
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("task")
    val task: String,
    @ColumnInfo("completed")
    val completed: Boolean,

)