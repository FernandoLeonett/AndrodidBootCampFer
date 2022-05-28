package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository

class ReservationRepository : IReservationRepository {

    override fun getAllReservations(): List<Reservation> = Provider.reservations(30)


    override fun addReservation(res: Reservation): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteReservation(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    operator fun invoke() = getAllReservations()
}