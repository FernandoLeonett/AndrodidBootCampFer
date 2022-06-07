package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bootcamp_android.data.repositories.LotRepository
import com.bootcamp_android.data.repositories.ParkingRepository
import com.bootcamp_android.domain.GetDetailALotUseCase
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.parking_app.viewmodel.lot_detail.LotDetailViewModel
import com.bootcamp_android.parking_app.viewmodel.lots.LotsViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass == LotsViewModel::class.java) {
            LotsViewModel(GetLotsUseCase().apply {
                parkingRepository = ParkingRepository() // use context
            }) as T
        } else if(modelClass == LotDetailViewModel::class.java) {
            LotDetailViewModel(GetDetailALotUseCase().apply {
                lotRepository = LotRepository() // use context
            }) as T
        } else {
            super.create(modelClass)
        }
    }
}