package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.AddReservationUseCase
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.Result
import kotlinx.coroutines.launch

class AddReservationViewModel(val addReservationUseCase: AddReservationUseCase) : ViewModel() {

    val mutableSuccessfulAdd = MutableLiveData<Boolean>()

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        if(reservation.authorizationCode != null && reservation.endDate != null && reservation.starDate != null && reservation.parkingLot != null) {
            when(addReservationUseCase(reservation)) {
                is Result.Success -> { //                    mutableSuccessfulAdd.postValue(true)
                    mutableSuccessfulAdd.postValue(true)
                }
                is Result.Failure -> {
                    mutableSuccessfulAdd.postValue(false)
                }
            }
        } else {
            mutableSuccessfulAdd.postValue(false)
        }
    }
}