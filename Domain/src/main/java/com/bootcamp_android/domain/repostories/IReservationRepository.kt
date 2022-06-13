package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Reservation

interface IReservationRepository {

   suspend fun addReservation(res: Reservation): Boolean // third screen
    suspend  fun deleteReservation(id: Int): Boolean // second screen
    suspend   fun getReservations(): List<Reservation>
}