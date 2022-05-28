package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Reservation

interface IReservationRepository {
    fun getAllReservations(): List<Reservation>//i need this method for the api

    fun addReservation(res: Reservation): Boolean// third screen
    fun deleteReservation(id: Int): Boolean// second screen
}