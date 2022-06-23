package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.CalculateFreeLotsUseCase
import com.bootcamp_android.domain.usecases.FillLotsUseCase
import com.bootcamp_android.domain.usecases.GetLotsUseCase
import com.bootcamp_android.domain.usecases.GetReservationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LotsViewModel(
    private val getLotsAvailableForReserve: GetLotsUseCase,
    private val getReservationsUseCase: GetReservationsUseCase,
    private val fillLotsUseCase: FillLotsUseCase,
    private val calculateFreeLotsUseCase: CalculateFreeLotsUseCase
) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }
    val lots: LiveData<List<Lot>> = _lots

    fun freeLots(lots: List<Lot>) = calculateFreeLotsUseCase(lots)

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = withContext(Dispatchers.IO) { getLotsAvailableForReserve.getLots() }
        val reservationResponse: List<Reservation> =
            withContext(Dispatchers.IO) { getReservationsUseCase.getReservation() }
        _lots.postValue(fillLotsUseCase.invoke(lotResponse,reservationResponse))
    }
}