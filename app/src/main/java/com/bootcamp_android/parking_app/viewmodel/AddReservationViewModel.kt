package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.AddReservationUseCase
import com.bootcamp_android.domain.usecases.GetLotsUseCase
import com.bootcamp_android.domain.util.AddResult
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
    fun validateDataUser(res :Reservation): AddResult.AddRequest {
        return AddResult.AddRequest(res.authorizationCode,res.startDate,res.endDate,res.parkingLot)
    }

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        when(withContext(Dispatchers.IO) { addReservationUseCase(reservation) }) {
            AddResult.AddResponse.SUCCESS -> successfulAdded.postValue(AddResult.AddResponse.SUCCESS)
            AddResult.AddResponse.ORDER -> successfulAdded.postValue(AddResult.AddResponse.ORDER)
            AddResult.AddResponse.BUSY -> successfulAdded.postValue(AddResult.AddResponse.BUSY)
            AddResult.AddResponse.REQUEST -> successfulAdded.postValue(AddResult.AddResponse.REQUEST)
            else -> successfulAdded.postValue(AddResult.AddResponse.Error)
        }
    }

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = withContext(Dispatchers.IO) { getLotsUseCase.getLots() }
        _lots.postValue(lotResponse)
    }
}
