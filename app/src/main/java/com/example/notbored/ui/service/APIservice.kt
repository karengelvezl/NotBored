package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET()
    suspend fun getRandom(): Response<SuggestionResponse>

    @GET()
    suspend fun getSuggestionByType(@Url type: String): Response<SuggestionResponse>

    @GET()
    suspend fun getSuggestionByParticipants(@Url participants : Int): Response<SuggestionResponse>

}