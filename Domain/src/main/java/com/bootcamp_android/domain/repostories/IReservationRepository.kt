package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.Result

interface IReservationRepository {


    suspend fun addReservation( res: Reservation): Result<Boolean>
    suspend fun deleteReservation(resID: String): Result<Boolean>
    suspend fun getReservations(): List<Reservation>
}