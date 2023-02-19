package com.example.teachmenotes.data.service

import com.example.teachmenotes.data.model.NoteColorsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/6dpDU4")
    suspend fun getColors(): Response<NoteColorsResponse>
}