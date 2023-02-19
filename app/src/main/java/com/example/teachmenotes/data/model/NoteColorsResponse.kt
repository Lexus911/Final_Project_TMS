package com.example.teachmenotes.data.model


data class NoteColorsResponse (
    val colorsList: List<Colors>
        )

data class Colors(
    val name: String,
    val value: String
)