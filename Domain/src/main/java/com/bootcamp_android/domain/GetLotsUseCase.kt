package com.bootcamp_android.domain

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.ILotsRepository
import com.bootcamp_android.domain.repostories.IReservationRepository

class GetLotsUseCase {

    lateinit var lotRepository: ILotsRepository

    suspend fun getLots() = lotRepository.getLots()







//     suspend fun fillAllLots(): List<Lot> {
//        var lotsWithReservations = reservationsRepository.getReservations().groupBy {
//            it.parkingLot
//        }.map {
//            Lot(it.key,it.value as MutableList<com.bootcamp_android.domain.model.Reservation>)
//        }
//        val allLots = lotRepository.getLots()
//        allLots.forEach { emptyLot ->
//            val any = lotsWithReservations.find {
//                it.id == emptyLot.id
//            }
//            if(any != null) {
//                emptyLot.reservations = any.reservations
//            }
//        }
//
//        return allLots
//    } //    private fun Lot.calculateTimeAvailable() {
    //        val actual: Long = 1300 //emulating the current time
    //        this.reservations.sortedBy { it.enDateDateTime } // really I need  order it
    //        val beforeReservations = this.reservations.filter {
    //            it.startDateTime < actual // filtering the reservations have begun before current time
    //        }
    //        var res = beforeReservations.last() // get the most recent reservation before current
    //
    //        if(res.enDateDateTime < actual) { // if the most recent reservation has finished then set its
    //            this.dateTimeAvailable = res.enDateDateTime
    //        } else {
    //            var index = reservations.indexOf(res)
    //            while (index < reservations.size - 1 && dateTimeAvailable == -1L) {
    //                var space = reservations[index + 1].startDateTime - reservations[index].enDateDateTime
    //                if(space > 0) {
    //                    dateTimeAvailable = reservations[index].enDateDateTime
    //                    if(index < reservations.size - 1) {
    //                        availableForTime = reservations[index + 1].startDateTime - dateTimeAvailable
    //                    }
    //                } else {
    //                    index++
    //                }
    //            }
    //            if(dateTimeAvailable == -1L) {
    //                dateTimeAvailable = reservations[index].enDateDateTime
    //            }
    //        }
    //    }
}





