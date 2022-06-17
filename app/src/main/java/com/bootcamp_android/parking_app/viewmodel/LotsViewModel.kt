package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.GetLotsUseCase
import com.bootcamp_android.domain.GetReservationsUseCase
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import kotlinx.coroutines.launch

class LotsViewModel(
    private val getLotsAvailableForReserve: GetLotsUseCase,private val getReservationsUseCase: GetReservationsUseCase
) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }
    private val _reservations: MutableLiveData<List<Reservation>> by lazy {
        MutableLiveData<List<Reservation>>()
    }
    val lots: LiveData<List<Lot>> = _lots

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = getLotsAvailableForReserve.getLots()
        val reservationResponse: List<Reservation> = getReservationsUseCase.getReservation()
        var lotsWithReservations = reservationResponse.groupBy {
            it.parkingLot
        }.map {
            Lot(it.key,it.value as MutableList<Reservation>)
        }
        lotResponse.forEach { emptyLot ->
            val any = lotsWithReservations.find {
                it.parkingLot == emptyLot.parkingLot
            }
            if(any != null) {
                emptyLot.reservations = any.reservations
            }
        }
        _lots.postValue(lotResponse)
    }
} //private val getLotListUseCase: GetLotListUseCase,
//private val getReservationListUseCase: GetReservationListUseCase
//) : ViewModel() {
//
//    private val _lots = MutableLiveData<List<LotReservation>>()
//    val lots: LiveData<List<LotReservation>> = _lots
//
//    private val _reservations = MutableLiveData<ReservationList>()
//    val reservations: LiveData<ReservationList>? = _reservations
//
//    private val _lotProgress = MutableLiveData<LotProgress>()
//
//    val lotProgress: LiveData<LotProgress> = _lotProgress
//
//
//    fun loadData() = viewModelScope.launch {
//        val lots = getLotListUseCase.getLots()
//        val reservations = getReservationListUseCase.getReservations()
//        val result = mutableListOf<LotReservation>()
//
//        lots.lotList.forEach {
//            val lotId = it.parkingLot
//            val lotReservation = LotReservation(lotId)
//            val reservationListLots = mutableListOf<com.bootcamp_android.domain.model.Reservation>()
//            reservations.reservationList.forEach {
//
//                if (lotId == it.parkingLot) {
//                    reservationListLots.add(it)
//
//                }
//            }
//            lotReservation.reswervationList = reservationListLots
//            result.add(lotReservation)
//        }
//        var lotsFree: Int = 0
//        var lotsBusy: Int = 0
//
//        result.forEach {
//            if (it.reswervationList?.isNotEmpty()!!) {
//                lotsBusy++
//            } else {
//                lotsFree++
//            }
//        }
//        val progressLot = LotProgress(lotsFree,lotsBusy)
//        _lotProgress.postValue(progressLot)
//        _lots.postValue(result)
//    }


