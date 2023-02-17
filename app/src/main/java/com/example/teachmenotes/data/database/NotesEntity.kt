package com.example.teachmenotes.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("NotesEntity")
data class NotesEntity (
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("note")
    val note: String,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("pinned")
    val pinned: Boolean,
        )