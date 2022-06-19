package com.bootcamp_android.parking_app.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.DeleteReservationUseCase
import com.bootcamp_android.domain.util.DeleteReservationRequest
import com.bootcamp_android.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationsViewModel(
    val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel() {

    val successfullyDeleted = MutableLiveData<DeleteReservationRequest>()
    lateinit var validateUserData: DeleteReservationRequest

    fun deleteReservation(reservation: Reservation,authorizationCode: String) = viewModelScope.launch {
        if(reservation.authorizationCode == authorizationCode) {
            when(withContext(Dispatchers.IO) { deleteReservationUseCase(reservation,authorizationCode) }) {
                is Result.Success -> {
                    successfullyDeleted.postValue(DeleteReservationRequest.SUCCESS)

                    Log.d(TAG,"deleteReservation: true")
                }
                is Result.Failure -> {
                    successfullyDeleted.postValue(DeleteReservationRequest.ERROR)
                    Log.d(TAG,"deleteReservation: false")
                }
            }
        } else { // bad code
            successfullyDeleted.postValue(DeleteReservationRequest.BAD_AUTHORIZATION_CODE)

        }
    }
}
