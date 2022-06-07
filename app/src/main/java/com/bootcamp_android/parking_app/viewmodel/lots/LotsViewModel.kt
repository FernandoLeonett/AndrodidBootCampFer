package com.bootcamp_android.parking_app.viewmodel.lots

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import kotlinx.coroutines.launch

class LotsViewModel(private val getLotsAvailableForReserve: GetLotsUseCase) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }
    val lots: LiveData<List<Lot>> = _lots

     fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = getLotsAvailableForReserve.fillAllLots()
        _lots.postValue(lotResponse)
    }
}