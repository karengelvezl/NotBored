package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    // base call to the API
    @GET()
    suspend fun getSuggestion(@Url random: String): Response<SuggestionResponse>

}