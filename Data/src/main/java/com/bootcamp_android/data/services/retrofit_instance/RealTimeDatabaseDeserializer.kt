package com.bootcamp_android.data.services.retrofit_instance

import com.bootcamp_android.data.services.response.ReservationListResponse
import com.bootcamp_android.domain.model.Reservation
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RealTimeDatabaseDeserializer : JsonDeserializer<ReservationListResponse> {

    companion object {
        private const val AUTHORIZATION_CODE = "authorizationCode"
        private const val START_DATE = "startDate"
        private const val END_DATE = "endDate"
        private const val PARKING_LOT = "parkingLot"
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ReservationListResponse {
        val eJson = json?.asJsonObject
        val keys = eJson?.keySet()
        var reservationListResponse = ReservationListResponse(mutableListOf())

        keys?.let {
            for (key in keys) {
                try {
                    val asJsonObject = eJson.get(key).asJsonObject
                    val reservationResponse = Reservation(
                        key,
                        asJsonObject.get(AUTHORIZATION_CODE).asString,asJsonObject.get(START_DATE).asLong,asJsonObject.get(
                            END_DATE
                        ).asLong,asJsonObject.get(PARKING_LOT).asInt
                    )

                    reservationListResponse.reservationList.add(reservationResponse)
                } catch (e: Exception) {

                }
            }
        }
        return reservationListResponse
    }
}