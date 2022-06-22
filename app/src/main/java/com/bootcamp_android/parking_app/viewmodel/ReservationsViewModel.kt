package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.DeleteReservationUseCase
import com.bootcamp_android.domain.util.DeleteReservationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationsViewModel(
    val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel() {

    val successfullyDeleted = MutableLiveData<DeleteReservationRequest>()
    lateinit var validateUserData: DeleteReservationRequest

    fun deleteReservation(reservation: Reservation,authorizationCode: String) = viewModelScope.launch {
        if(reservation.authorizationCode != "") {

            when(withContext(Dispatchers.IO) { deleteReservationUseCase(reservation,authorizationCode) }) {
                DeleteReservationRequest.SUCCESS_RESULT -> {
                    successfullyDeleted.postValue(DeleteReservationRequest.SUCCESS_RESULT)
                }
                DeleteReservationRequest.BAD_AUTHORIZATION_CODE -> {
                    successfullyDeleted.postValue(DeleteReservationRequest.BAD_AUTHORIZATION_CODE)
                }
                else -> {
                    successfullyDeleted.postValue(DeleteReservationRequest.ERROR)
                }
            }
        } else {
            validateUserData = DeleteReservationRequest.BAD_AUTHORIZATION_CODE
        }
    }
}
