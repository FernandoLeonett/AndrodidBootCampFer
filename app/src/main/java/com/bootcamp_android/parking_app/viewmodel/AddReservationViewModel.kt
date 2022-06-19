package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.AddReservationUseCase
import com.bootcamp_android.domain.usecases.GetLotsUseCase
import com.bootcamp_android.domain.usecases.ValidateReservationUseCase
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.parking_app.utils.AddReservationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddReservationViewModel(
    val addReservationUseCase: AddReservationUseCase,
    val validateReservationUseCase: ValidateReservationUseCase,
    private val getLotsUseCase: GetLotsUseCase
) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }
    val lots: LiveData<List<Lot>> = _lots
    val successfulAdded = MutableLiveData<AddResult>()
    lateinit var validateUserData: AddReservationRequest

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        validateUserData = AddReservationRequest(reservation)
        if(validateUserData.successRequest) {
            if((withContext(Dispatchers.IO) { validateReservationUseCase(reservation) } == AddResult.IS_FREE)) {
                when(withContext(Dispatchers.IO) { addReservationUseCase(reservation) }) {
                    is Result.Success -> { //                    successfulAdded.postValue(true)
                        successfulAdded.postValue(AddResult.IS_FREE)
                    }
                    is Result.Failure -> {
                        successfulAdded.postValue(AddResult.IS_BUSY)
                    }
                }
            } else {
                successfulAdded.postValue(AddResult.Error)
            }
        }
    }

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = withContext(Dispatchers.IO) { getLotsUseCase.getLots() }
        _lots.postValue(lotResponse)
    }
}
