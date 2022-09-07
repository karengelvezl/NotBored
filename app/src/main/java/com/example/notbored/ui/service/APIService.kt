package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET()
    suspend fun getRandom(): Response<SuggestionResponse>
/*
    @GET ("parkings/{parkingId}/.json")
    suspend fun getLotList(@Path("parkingId") id: String) : Response<LotListResponse>

    @DELETE("{parkingId}/reservations/{reservationId}/.json")
    suspend fun deleteReservation(@Path("parkingId") id: String, @Path("reservationId") resId: String) : Response<Any>

    @POST("{parkingId}/reservations.json?=")
    suspend fun postReservation(@Path("parkingId") id: String, @Body newReservation: ReservationRequest) : Response<Any>
*/
}