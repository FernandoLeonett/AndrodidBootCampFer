package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.model.Lot

class LotsViewModel(val getLots: GetLotsUseCase) : ViewModel() {

    fun requireLots(): List<Lot> = getLots();
}