package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import com.codelab.domain.GetReservationsUseCase

class ReservationsViewModel(val getReservations: GetReservationsUseCase) : ViewModel() {

    fun requireReservations() = getReservations()
}