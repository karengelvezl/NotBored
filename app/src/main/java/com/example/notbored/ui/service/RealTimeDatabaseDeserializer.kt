/*package com.example.notbored.ui.service

import com.example.notbored.ui.service.responseObjects.SuggestionListResponse
import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RealTimeDatabaseDeserializer : JsonDeserializer<SuggestionResponse> {

    companion object {
        private const val AUTHORIZATION_CODE = "authorizationCode"
        private const val START_DATE = "startDate"
        private const val END_DATE = "endDate"
        private const val PARKING_LOT = "parkingLot"
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): SuggestionListResponse {
        val eJson = json?.asJsonObject
        val keys = eJson?.keySet()
        var reservationResponse: SuggestionResponse
        var reservationListResponse = SuggestionListResponse(mutableListOf<SuggestionResponse>())

        keys?.let {
            for (key in keys) {
                try {
                    val asJsonObject = eJson.get(key).asJsonObject
                    reservationResponse = SuggestionResponse(
                        key,
                        asJsonObject.get(AUTHORIZATION_CODE).asString, asJsonObject.get(START_DATE).asString, asJsonObject.get(
                            END_DATE
                        ).asString, asJsonObject.get(PARKING_LOT).asInt
                    )

                    reservationListResponse.reservationList.add(reservationResponse)
                } catch (e: Exception) {

                }
            }
        }
        return reservationListResponse
    }
}*/