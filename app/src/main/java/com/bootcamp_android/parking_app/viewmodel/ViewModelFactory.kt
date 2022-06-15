package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bootcamp_android.data.repositories.LotRepository
import com.bootcamp_android.data.repositories.ReservationRepository
import com.bootcamp_android.domain.AddReservationUseCase
import com.bootcamp_android.domain.DeleteReservationUseCase
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.GetReservationsUseCase
import com.bootcamp_android.parking_app.viewmodel.lot_detail.ReservationsViewModel
import com.bootcamp_android.parking_app.viewmodel.lots.LotsViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass == LotsViewModel::class.java) {
            LotsViewModel(GetLotsUseCase().apply {
                lotRepository = LotRepository() // use context
            },GetReservationsUseCase().apply {
                reservationRepository = ReservationRepository()
            }) as T
        } else if(modelClass == ReservationsViewModel::class.java) {
            ReservationsViewModel(AddReservationUseCase().apply {
                addReservationRepository = ReservationRepository() // use context
            },DeleteReservationUseCase().apply {
                deleteRepository = ReservationRepository()
            }) as T
        } else {
            super.create(modelClass)
        }
    }
}