package com.bootcamp_android.parking_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.usecases.GetLotsUseCase
import com.bootcamp_android.domain.usecases.GetReservationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LotsViewModel(
    private val getLotsAvailableForReserve: GetLotsUseCase,private val getReservationsUseCase: GetReservationsUseCase
) : ViewModel() {

    private val _lots: MutableLiveData<List<Lot>> by lazy {
        MutableLiveData<List<Lot>>()
    }

//        private val _reservations: MutableLiveData<List<Reservation>> by lazy {
//            MutableLiveData<List<Reservation>>()
//        }
    val lots: LiveData<List<Lot>> = _lots

    fun geNumberOfFreeLots(lots: List<Lot>) = lots.count {
        it.dateTimeAvailable == -1L
    }

    fun loadLots() = viewModelScope.launch {
        val lotResponse: List<Lot> = withContext(Dispatchers.IO) { getLotsAvailableForReserve.getLots() }
        val reservationResponse: List<Reservation> =
            withContext(Dispatchers.IO) { getReservationsUseCase.getReservation() }
        var lotsWithReservations = reservationResponse.groupBy {
            it.parkingLot
        }.map {
            Lot(it.key,it.value as MutableList<Reservation>)
        }
        lotResponse.forEach { emptyLot ->
            val lot = lotsWithReservations.find {
                it.parkingLot == emptyLot.parkingLot
            }
            if(lot != null) {
                emptyLot.reservations = lot.reservations
            }
            emptyLot.calculateTimeAvailable()
        }
        _lots.postValue(lotResponse)
    }

//    private fun Lot.calculateTimeAvailable() {
//        if(this.reservations.size > 0) {
//            val actual: Long = System.currentTimeMillis()
//            val res =
//                if(this.reservations.size == 1) reservations[0] else // filtering the reservations have begun before current time
//                    reservations.sortedBy { it.endDate }.last {
//                        it.startDate < actual // filtering the reservations have begun before current time
//                    } // get the most recent reservation before current
//
//            if(res.endDate < actual) { // if the most recent reservation has finished then set its
//                //                this.dateTimeAvailable = res.endDate
//                this.free = true
//            } else if(res.startDate > actual) {
//                this.free = true
//            } else {
//                var index = reservations.indexOf(res)
//                while (index < reservations.size - 1 && dateTimeAvailable == -1L) {
//                    var space = reservations[index + 1].startDate - reservations[index].endDate
//                    if(space > 0) {
//                        dateTimeAvailable = reservations[index].endDate
//                        if(index < reservations.size - 1) {
//                            availableForTime = space
//                        }
//                    } else {
//                        index++
//                    }
//                }
//                if(dateTimeAvailable == -1L) {
//                    dateTimeAvailable = reservations[index].endDate //
//                }
//            }
//        } else {
//            this.free = true
//        }
//    }
} //private val getLotListUseCase: GetLotListUseCase, //private val getReservationListUseCase: GetReservationListUseCase //) : ViewModel() { // //    private val _lots = MutableLiveData<List<LotReservation>>() //    val lots: LiveData<List<LotReservation>> = _lots
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


