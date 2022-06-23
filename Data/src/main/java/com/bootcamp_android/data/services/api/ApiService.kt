package com.bootcamp_android.data.services.api

import com.bootcamp_android.data.services.request.ReservationRequest
import com.bootcamp_android.data.services.response.ParkingLotListResponse
import com.bootcamp_android.data.services.response.ReservationListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @DELETE("{PARKING_ID}/reservations/{reservationId}.json")
    suspend fun deleteReservation(
        @Path("PARKING_ID") parkingId: String,
        @Path("reservationId") reservationId: String
    ): Response<Any?>

    @POST("{PARKING_ID}/reservations.json")
    suspend fun addReservations(
        @Path("PARKING_ID") id: String,
        @Body reservationRequest: ReservationRequest
    ): Response<Any>

    @GET("parkings/{PARKING_ID}/.json")
    suspend fun getParkingLots(@Path("PARKING_ID") id: String): ParkingLotListResponse

    @GET("{PARKING_ID}/reservations.json")
    suspend fun getReservations(@Path("PARKING_ID") id: String): ReservationListResponse?
}