package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET()
    suspend fun getRandom(): Response<SuggestionResponse>

    @GET("type={type}")
    suspend fun getSuggestionByType(@Path("type") type: String): Response<SuggestionResponse>

    @GET("participants={participants}")
    suspend fun getSuggestionByParticipants(@Path("participants") participants : Int): Response<SuggestionResponse>

    @GET("price={price}")
    suspend fun getSuggestionByPrice(@Path("price") price : Int): Response<SuggestionResponse>

    @GET("accessibility={accessibility}")
    suspend fun getSuggestionByAccessibility(@Path("accessibility")accessibility:Int): Response<SuggestionResponse>

}