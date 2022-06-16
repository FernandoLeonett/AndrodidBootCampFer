package com.bootcamp_android.data.services.api


import com.bootcamp_android.data.services.request.ReservationResponse
import com.bootcamp_android.data.services.response.ParkingLotListResponse
import com.bootcamp_android.data.services.response.ReservationListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @DELETE("{parkingId}/reservations/{reservationId}.json")
    suspend fun deleteReservation(
        @Path("parkingId") parkingId: String,
        @Path("reservationId") reservationId: String
    ): Response<Any?>

    @POST("{parkingId}/reservations.json")
    suspend fun addReservations(
        @Path("parkingId") id: String,
        @Body reservation: ReservationResponse
    ): Response<Any?>

    @GET("parkings/{parkingId}/.json")
    suspend fun getParkingLots(@Path("parkingId") id: String): ParkingLotListResponse

    @GET("{parkingId}/reservations.json")
    suspend fun getReservations(@Path("parkingId") id: String): ReservationListResponse?
}