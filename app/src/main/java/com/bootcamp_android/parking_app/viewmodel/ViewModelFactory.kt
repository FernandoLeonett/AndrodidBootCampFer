package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bootcamp_android.data.repositories.LotRepository
import com.bootcamp_android.data.repositories.ReservationRepository
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.GetReservationsUseCase

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass == LotsViewModel::class.java) {
            LotsViewModel(GetLotsUseCase().apply {
                lotsRepository = LotRepository() // use context
            }) as T
        } else if(modelClass == ReservationsViewModel::class.java) {
            ReservationsViewModel(GetReservationsUseCase().apply {
                reservationRepository = ReservationRepository() // use context
            }) as T
        } else {
            super.create(modelClass)
        }
    }
}