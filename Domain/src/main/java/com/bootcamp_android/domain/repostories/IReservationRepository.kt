package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.LotDetail

interface IReservationRepository {
    fun getAllReservations(): List<LotDetail>//i need this method for the api
    fun getReservationsCurrentAvailability(): List<LotDetail>//these are for the main screen
    fun addReservation(res: LotDetail): Boolean// third screen
    fun deleteReservation(id: Int): Boolean// second screen
}