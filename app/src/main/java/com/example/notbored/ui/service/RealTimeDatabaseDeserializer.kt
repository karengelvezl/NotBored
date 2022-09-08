package com.example.data.service

import com.example.data.responseObjects.ReservationListResponse
import com.example.data.responseObjects.ReservationResponse
import com.example.notbored.ui.service.responseObjects.SuggestionListResponse
import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RealTimeDatabaseDeserializer : JsonDeserializer<SuggestionListResponse> {

    companion object {
        private const val ACTIVITY = "activity"
        private const val TYPE = "type"
        private const val PARTICIPANTS = "participants"
        private const val PRICE = "price"
        private const val LINK = "link"
        private const val KEY = "key"
        private const val ACCESSIBILITY = "accessibility"
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
                        asJsonObject.get(ACTIVITY).asString,
                        asJsonObject.get(TYPE).asString,
                        asJsonObject.get(PARTICIPANTS).asInt,
                        asJsonObject.get(PRICE).asInt,
                        asJsonObject.get(LINK).asString,
                        asJsonObject.get(KEY ).asString,
                        asJsonObject.get(ACCESSIBILITY).asInt
                    )

                    reservationListResponse.suggestionList.add(reservationResponse)
                } catch (e: Exception) {

                }
            }
        }
        return reservationListResponse
    }
}