package com.bootcamp_android.parking_app.viewmodel

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
            if(System.currentTimeMillis() !in reservation.startDate..reservation.endDate) {
                validateUserData = DeleteReservationRequest.SUCCESS_REQUEST

                when(withContext(Dispatchers.IO) { deleteReservationUseCase(reservation,authorizationCode) }) {
                    is Result.Success -> {
                        successfullyDeleted.postValue(DeleteReservationRequest.SUCCESS_RESULT)
                    }
                    is Result.Failure -> {
                        successfullyDeleted.postValue(DeleteReservationRequest.ERROR)
                    }
                }
            } else { // bad code
                validateUserData = DeleteReservationRequest.CURRENT_RESERVATION
            }
        } else {
            validateUserData = DeleteReservationRequest.BAD_AUTHORIZATION_CODE
        }
    }
}
