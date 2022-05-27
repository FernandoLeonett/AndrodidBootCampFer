package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import com.bootcamp_android.domain.GetReservationsUseCase

class ReservationsViewModel(private val getReservations: GetReservationsUseCase) : ViewModel() {

    fun requireReservations() = getReservations()


}