package com.codelab.domain.repostories

import com.codelab.domain.model.Reservation

open interface IReservationRepository {

    fun getReservations(): List<Reservation>
}