package com.bootcamp_android.parking_app.viewmodel

import android.hardware.camera2.CameraManager
import androidx.lifecycle.ViewModel
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.GetReservationsUseCase
import com.bootcamp_android.domain.model.Lot

class LotsViewModel(val getLotsWithCurrentAvailability: GetLotsUseCase) : ViewModel() {


}