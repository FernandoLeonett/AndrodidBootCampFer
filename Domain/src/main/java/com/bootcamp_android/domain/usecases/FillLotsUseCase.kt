package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation

class FillLotsUseCase {

    operator fun invoke(lots: List<Lot>,res: List<Reservation>) = fillLots(lots,res)

    private fun fillLots(lots: List<Lot>,reservations: List<Reservation>): List<Lot> {
        val lotsWithReservations = reservations.groupBy {
            it.parkingLot
        }.map {
            Lot(it.key,it.value as MutableList<Reservation>)
        }
        lots.forEach { emptyLot ->
            val lot = lotsWithReservations.find {
                it.parkingLot == emptyLot.parkingLot
            }
            if(lot != null) {
                emptyLot.reservations = lot.reservations
            }
      
            emptyLot.calculateTimeAvailable()
        }
        return lots
    }

    private fun Lot.calculateTimeAvailable() {
        if(this.reservations.size > 0) {
            val actual: Long = System.currentTimeMillis()
            val res =
                if(this.reservations.size == 1) reservations[0] else // filtering the reservations have begun before current time
                    reservations.sortedBy { it.endDate }.last {
                        it.startDate < actual // filtering the reservations have begun before current time
                    } // get the most recent reservation before current

            if(res.endDate < actual) { // if the most recent reservation has finished then set its
                //                this.dateTimeAvailable = res.endDate
                this.free = true
            } else if(res.startDate > actual) {
                this.free = true
                availableForTime =res.startDate-actual
            } else {
                var index = reservations.indexOf(res)
                while (index < reservations.size - 1 && dateTimeAvailable == -1L) {
                    val space = reservations[index + 1].startDate - reservations[index].endDate
                    if(space > 0) {
                        dateTimeAvailable = reservations[index].endDate
                        if(index < reservations.size - 1) {
                            this.availableForTime = space
                        }
                    } else {
                        index++
                    }
                }
                if(dateTimeAvailable == -1L) {
                    dateTimeAvailable = reservations[index].endDate //
                }
            }
        } else {
            this.free = true
        }
    }
}