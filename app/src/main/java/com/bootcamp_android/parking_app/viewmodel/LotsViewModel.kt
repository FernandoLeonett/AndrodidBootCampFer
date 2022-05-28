package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.ViewModel
import com.bootcamp_android.domain.GetLotsUseCase

class LotsViewModel(val getLotsWithCurrentAvailability: GetLotsUseCase) : ViewModel() {


}