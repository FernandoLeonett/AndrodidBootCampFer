package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Reservation

open interface IReservationRepository {

    fun getReservations(): List<Reservation>
}