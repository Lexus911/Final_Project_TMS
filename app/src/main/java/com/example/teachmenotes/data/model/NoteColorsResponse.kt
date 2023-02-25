package com.example.teachmenotes.data.model

import com.google.gson.annotations.SerializedName


data class NoteColorsResponse (
@SerializedName("colors")
val colorsList: List<Colors>
)

data class Colors(
    val value: String,
)