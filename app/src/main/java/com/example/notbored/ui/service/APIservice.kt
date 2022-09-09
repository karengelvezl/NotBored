package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    // base call to the API
    @GET("activity")
    suspend fun getRandomSuggestion(): Response<SuggestionResponse>

    @GET("activity")
    suspend fun getSuggestionByParticipants(
        @Query("participants") participants: Int
    ): Response<SuggestionResponse>

    @GET("activity")
    suspend fun getSuggestionByActivity(
        @Query("type") activity: String
    ): Response<SuggestionResponse>

    @GET("activity")
    suspend fun getSuggestionByActivityAndParticipants(
        @Query("type") activity: String,
        @Query("participants") participants: Int
    ): Response<SuggestionResponse>
}