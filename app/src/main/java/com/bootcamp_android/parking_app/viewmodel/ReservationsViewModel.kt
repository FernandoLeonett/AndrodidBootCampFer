package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.DeleteReservationUseCase
import com.bootcamp_android.domain.util.DeleteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationsViewModel(
    val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel() {

    var successfullyDeleted = MutableLiveData<DeleteResult>()

    fun deleteReservation(reservation: Reservation,authorizationCode: String) = viewModelScope.launch {
        when(withContext(Dispatchers.IO) { deleteReservationUseCase(reservation,authorizationCode) }) {
            DeleteResult.SUCCESS_RESULT -> successfullyDeleted.postValue(DeleteResult.SUCCESS_RESULT)
            DeleteResult.BAD_AUTHORIZATION_CODE -> successfullyDeleted.postValue(DeleteResult.BAD_AUTHORIZATION_CODE)
            else -> successfullyDeleted.postValue(DeleteResult.ERROR)
        }
    }
}
