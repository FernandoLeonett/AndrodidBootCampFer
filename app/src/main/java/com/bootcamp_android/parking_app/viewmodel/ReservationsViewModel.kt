package com.bootcamp_android.parking_app.viewmodel

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
   val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel() {


    val mutableSuccessfulDelete = MutableLiveData<Boolean>()

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
