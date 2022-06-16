package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.Result

interface IReservationRepository {

    suspend fun addReservation(
    reservation: Reservation
    ): Result<Boolean>

    suspend fun deleteReservation(
         reservation :Reservation,authorizationCode: String
    ): Result<Boolean>

    suspend fun getReservations(): List<Reservation>
}