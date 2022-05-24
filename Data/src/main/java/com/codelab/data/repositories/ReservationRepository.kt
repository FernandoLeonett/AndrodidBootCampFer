package com.codelab.data.repositories

import com.codelab.data.repositories.Provider.Companion.reservations

import com.codelab.domain.model.Reservation
import com.codelab.domain.repostories.IReservationRepository

class ReservationRepository : IReservationRepository { //interface
    override fun getReservations(): List<Reservation> = reservations
}