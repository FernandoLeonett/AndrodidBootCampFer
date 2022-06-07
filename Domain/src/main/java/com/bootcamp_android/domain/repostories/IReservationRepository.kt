package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Reservation

interface IReservationRepository {

    fun addReservation(res: Reservation): Boolean // third screen
    fun deleteReservation(id: Int): Boolean // second screen
    fun getReservations(): List<Reservation>
}