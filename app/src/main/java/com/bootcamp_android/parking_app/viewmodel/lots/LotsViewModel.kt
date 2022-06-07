package com.bootcamp_android.parking_app.viewmodel.lots

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.model.Lot
import kotlinx.coroutines.launch

class LotsViewModel(private val getLotsAvailableForReserve: GetLotsUseCase) : ViewModel() {

     val lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>().also {
            loadLots()
        }
    }

//    fun getLots(): LiveData<List<Lot>> {
//        return lots
//    }

    fun loadLots() = viewModelScope.launch {

    }
}