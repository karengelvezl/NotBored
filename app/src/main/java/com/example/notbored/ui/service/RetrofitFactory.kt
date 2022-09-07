package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitFactory {

    private const val BASE_URL = "http://www.boredapi.com/api/activity/?"

    private val httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
            .client(httpClient.build())
            .build()
    }

    private fun getGsonBuilder() =
        GsonBuilder().registerTypeAdapter(SuggestionResponse::class.java, RealTimeDatabaseDeserializer()).create()
}


