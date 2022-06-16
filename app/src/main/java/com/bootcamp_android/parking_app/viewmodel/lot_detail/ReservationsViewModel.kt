package com.bootcamp_android.parking_app.viewmodel.lot_detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.AddReservationUseCase
import com.bootcamp_android.domain.DeleteReservationUseCase
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils
import kotlinx.coroutines.launch

class ReservationsViewModel(
    val addReservationUseCase: AddReservationUseCase,val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel() {


    val mutableSuccessfulAdd = MutableLiveData<Boolean>()
    val mutableSuccessfulDelete = MutableLiveData<Boolean>()

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        if(reservation.authorizationCode != null && reservation.endDateTimeInMillis != null && reservation.starDateTimeInMillis != null && reservation.parkingLot != null) {
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

    fun deleteReservation(reservation: Reservation,authorizationCode: String) = viewModelScope.launch {
        if(reservation.authorizationCode == authorizationCode) {
            when(deleteReservationUseCase(reservation,authorizationCode)) {
                is Result.Success -> {
                    mutableSuccessfulDelete.postValue(true)

                    Log.d(TAG,"deleteReservation: true")
                }
                is Result.Failure -> {
                    mutableSuccessfulDelete.postValue(false)
                    Log.d(TAG,"deleteReservation: false")
                }
            }
        } else {
            // bad code
            mutableSuccessfulDelete.postValue(false)
            Log.d(TAG,"deleteReservation: codigo mal")
        }
    }
}
