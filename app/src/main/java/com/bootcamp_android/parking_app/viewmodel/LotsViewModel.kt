package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import com.codelab.domain.GetLotsUseCase
import com.codelab.domain.model.Lot

class LotsViewModel(val getLots: GetLotsUseCase) : ViewModel() {

    fun requireLots(): List<Lot> = getLots();
}