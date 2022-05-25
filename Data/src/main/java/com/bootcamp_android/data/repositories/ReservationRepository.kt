package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.repositories.Provider.Companion.reservations

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository

class ReservationRepository : IReservationRepository { //interface
    override fun getReservations(): List<Reservation> = reservations
}