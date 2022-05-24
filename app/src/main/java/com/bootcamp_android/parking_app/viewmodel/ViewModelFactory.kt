package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelab.data.repositories.LotRepository
import com.codelab.data.repositories.ReservationRepository
import com.codelab.domain.GetLotsUseCase
import com.codelab.domain.GetReservationsUseCase

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