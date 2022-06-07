package com.bootcamp_android.parking_app.viewmodel.lot_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp_android.domain.GetDetailALotUseCase
import com.bootcamp_android.domain.model.Lot

class LotDetailViewModel(private val geLotDetail: GetDetailALotUseCase) : ViewModel() {

     val lot = MutableLiveData<Lot?>()
    val lots: MutableLiveData<Lot?> = lot

    fun lot(id: Int) {

    }
}