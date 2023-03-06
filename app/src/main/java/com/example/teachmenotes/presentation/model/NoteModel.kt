package com.example.teachmenotes.presentation.model

import android.text.Spannable
import android.text.SpannableString

data class NoteModel(
    val id: Int?,
    var title: String,
    var note: String,
    val date: String,
    val color: String,
)
