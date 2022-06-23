package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.GetLotsUseCase
import com.bootcamp_android.domain.usecases.AddReservationUseCase
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.AddReservationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddReservationViewModel(
    val addReservationUseCase: AddReservationUseCase,private val getLotsUseCase: GetLotsUseCase
) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }
    val lots: LiveData<List<Lot>> = _lots
    var successfulAdded = MutableLiveData<AddResult>()
    fun validateDataUser(reservation: Reservation): AddReservationRequest {
        return AddReservationRequest(reservation)
    }

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        when(withContext(Dispatchers.IO) { addReservationUseCase(reservation) }) {
            AddResult.SUCCESS -> successfulAdded.postValue(AddResult.SUCCESS)
            AddResult.ORDER_DATE -> successfulAdded.postValue(AddResult.ORDER_DATE)
            AddResult.IS_BUSY -> successfulAdded.postValue(AddResult.IS_BUSY)
            AddResult.BAD_REQUEST -> successfulAdded.postValue(AddResult.BAD_REQUEST)
            else -> successfulAdded.postValue(AddResult.Error)
        }
    }

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = withContext(Dispatchers.IO) { getLotsUseCase.getLots() }
        _lots.postValue(lotResponse)
    }
}
