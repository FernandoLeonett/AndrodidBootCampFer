package com.bootcamp_android.data.database

import com.bootcamp_android.data.models.Reservation
import com.bootcamp_android.domain.model.Lot

interface IReservationDao {

    fun getReservations(): List<Reservation>
}