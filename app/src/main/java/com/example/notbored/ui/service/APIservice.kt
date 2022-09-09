package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    // base call to the API
    @GET()
    suspend fun getSuggestion(@Url endOfUrl: String): Response<SuggestionResponse>

    @GET("activity?")
    suspend fun getActivity(@Query("type") activity: String?, @Query("participants") participants: Int?): Response<SuggestionResponse>
    
    @GET("activity?")
    suspend fun getRandomActivity(@Query("participants") participants: Int): Response<SuggestionResponse>
}