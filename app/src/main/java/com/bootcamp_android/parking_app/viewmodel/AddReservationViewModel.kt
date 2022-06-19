package com.bootcamp_android.parking_app.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.AddReservationUseCase
import com.bootcamp_android.domain.usecases.GetLotsUseCase
import com.bootcamp_android.domain.usecases.ValidateReservationUseCase
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.parking_app.utils.Utils
import com.bootcamp_android.parking_app.utils.Utils.formatDateForLotList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

class AddReservationViewModel(
    val addReservationUseCase: AddReservationUseCase,
    val validateReservationUseCase: ValidateReservationUseCase,
    private val getLotsUseCase: GetLotsUseCase
) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }
    val lots: LiveData<List<Lot>> = _lots
    val mutableSuccessfulAdd = MutableLiveData<Boolean>()

    fun addReservation(reservation: Reservation) = viewModelScope.launch {

        Log.d(TAG,"FechaInicio :${formatDateForLotList(reservation.startDate)}")
        Log.d(TAG,"fechaFinal :${formatDateForLotList(reservation.endDate)}")
        if(reservation.authorizationCode != "" && reservation.endDate != -1L && reservation.startDate != -1L && reservation.endDate > reservation.startDate && reservation.parkingLot!=-1) {
            if(withContext(Dispatchers.IO) { validateReservationUseCase(reservation) }) {
                when(withContext(Dispatchers.IO) { addReservationUseCase(reservation) }) {
                    is Result.Success -> { //                    mutableSuccessfulAdd.postValue(true)
                        mutableSuccessfulAdd.postValue(true)

                    }
                    is Result.Failure -> {
                        mutableSuccessfulAdd.postValue(false)
                        Log.d(TAG,"addr false")
                    }
                }
            } else {
                mutableSuccessfulAdd.postValue(false)
                Log.d(TAG,"addr no valida")
            }
        } else {
            mutableSuccessfulAdd.postValue(false)
            Log.d(TAG,"addr no valida mal ingreso")
        }
    }

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = withContext(Dispatchers.IO) { getLotsUseCase.getLots() }
        _lots.postValue(lotResponse)
    }
}
