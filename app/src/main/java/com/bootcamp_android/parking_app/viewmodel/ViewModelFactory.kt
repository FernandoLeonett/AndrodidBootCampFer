package com.bootcamp_android.parking_app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bootcamp_android.data.repositories.LotRepository
import com.bootcamp_android.data.repositories.ReservationRepository
import com.bootcamp_android.data.room.localdatabase.ParkingDataBase
import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.domain.usecases.*

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass == LotsViewModel::class.java) {
            LotsViewModel(GetLotsUseCase().apply {
                lotRepository = LotRepository(
                    ParkingService(),ParkingDataBase.getInstance(context)
                )
            },GetReservationsUseCase().apply {
                reservationRepository = ReservationRepository(
                    ParkingService(),ParkingDataBase.getInstance(context)
                )
            }) as T
        } else if(modelClass == ReservationsViewModel::class.java) {
            ReservationsViewModel(DeleteReservationUseCase().apply {
                deleteRepository = ReservationRepository(
                    ParkingService(),ParkingDataBase.getInstance(context)
                )
            }) as T
        } else if(modelClass == AddReservationViewModel::class.java) {
            AddReservationViewModel(AddReservationUseCase().apply {
                addReservationRepository = ReservationRepository(
                    ParkingService(),ParkingDataBase.getInstance(context)
                )
            },ValidateReservationUseCase().apply {
                validateReservationRepository = LotRepository(
                    ParkingService(),ParkingDataBase.getInstance(context)
                )
            },GetLotsUseCase().apply {
                lotRepository = LotRepository(
                    ParkingService(),ParkingDataBase.getInstance(context)
                )
            }) as T
        } else {
            super.create(modelClass)
        }
    }
}