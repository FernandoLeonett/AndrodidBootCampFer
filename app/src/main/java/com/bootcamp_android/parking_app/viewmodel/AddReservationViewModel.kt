package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.usecases.AddReservationUseCase
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.ValidateReservationUseCase
import com.bootcamp_android.domain.util.Result
import kotlinx.coroutines.launch

class AddReservationViewModel(val addReservationUseCase: AddReservationUseCase, val validateReservationUseCase: ValidateReservationUseCase) : ViewModel() {

    val mutableSuccessfulAdd = MutableLiveData<Boolean>()

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        if(reservation.authorizationCode != "" && reservation.endDate != -1L && reservation.starDate != -1L) {
            if(validateReservationUseCase(reservation)) {
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
}
