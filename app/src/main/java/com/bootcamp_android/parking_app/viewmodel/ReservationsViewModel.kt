package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import com.bootcamp_android.domain.GetLotsAvailableForReserve
import com.bootcamp_android.domain.model.Reservation

class ReservationsViewModel(private val getReservations: GetLotsAvailableForReserve) : ViewModel() {

    fun requireReservations():List<Reservation> = getReservations()


}