package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.Result
import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BoringService (private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun getRandomSuggestion() : Result<SuggestionResponse> {
        var result : Result<SuggestionResponse>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getRandom()
                if(callResponse.isSuccessful){
                    Result.Success((callResponse.body()))
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }
/*
    suspend fun getReservationList() : Result<ReservationListResponse?> {
        var result : Result<ReservationListResponse?>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getReservationList(parkingId)
                if(callResponse.isSuccessful){
                    Result.Success((callResponse.body()))
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun deleteReservation(reservationId: String): Result<Boolean>{
        var result : Result<Boolean>
        withContext(dispatcher){
        result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).deleteReservation(parkingId,reservationId)
                if(callResponse.isSuccessful){
                    Result.Success(true)
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun addReservation(reservation: ReservationRequest) : Result<Boolean> {
        var result : Result<Boolean>
        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).postReservation(parkingId,reservation)
                if(callResponse.isSuccessful){
                    Result.Success(true)
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }
    */
}