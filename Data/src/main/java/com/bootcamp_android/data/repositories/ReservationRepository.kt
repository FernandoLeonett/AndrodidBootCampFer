package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository

class ReservationRepository : IReservationRepository {


    private lateinit var lotRepository: LotRepository

    override fun addReservation(res: Reservation): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteReservation(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getReservations(): List<Reservation> {
        return Provider.reservations(30,13)
    }
}