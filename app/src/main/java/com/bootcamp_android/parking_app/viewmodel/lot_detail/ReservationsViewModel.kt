package com.bootcamp_android.parking_app.viewmodel.lot_detail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.AddReservationUseCase
import com.bootcamp_android.domain.DeleteReservationUseCase
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils
import com.bootcamp_android.domain.util.Utils.parkingId
import kotlinx.coroutines.launch


class ReservationsViewModel(val addReservationUseCase: AddReservationUseCase, val deleteReservationUseCase: DeleteReservationUseCase): ViewModel() {

    private val parkingId = Utils.parkingId
    private val mutableSuccessfulAdd = MutableLiveData<Boolean>()
    private val mutableSuccessfulDelete = MutableLiveData<Boolean>()
    val successfulDelete: LiveData<Boolean> = mutableSuccessfulDelete

    val successfulAdd: LiveData<Boolean> = mutableSuccessfulAdd

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        if(reservation.authorizationCode != null && reservation.endDateTimeInMillis != null && reservation.starDateTimeInMillis != null && reservation.parkingLot != null) {
            when(addReservationUseCase(parkingId,reservation)) {
                is Result.Success -> {
//                    mutableSuccessfulAdd.postValue(true)
                 mutableSuccessfulAdd.value =true
                }
                is Result.Failure -> {
                    mutableSuccessfulAdd.postValue(false)
                }
            }
        } else {
            mutableSuccessfulAdd.postValue(false)
        }
    }



    fun deleteReservation(reservation:Reservation,authorizationCode: String) = viewModelScope.launch {
        if(reservation.authorizationCode == authorizationCode) {
            when(deleteReservationUseCase(reservation, authorizationCode)) {
                is Result.Success -> {
                    mutableSuccessfulDelete.value = true
                }
                is Result.Failure -> {
                    mutableSuccessfulDelete.value = false
                }
            }
        } else {
            mutableSuccessfulDelete.value = false
        }
    }

}
