package com.bootcamp_android.domain

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.repostories.IParkingRepository

class GetLotsUseCase {

    lateinit var parkingRepository: IParkingRepository

    operator fun invoke(): List<Lot> = getLots()
    private fun getLots(): List<Lot> {
        val parking = parkingRepository.getParking()
        parking.lotsForReserve.forEach {
            it.calculateTimeAvailable()
        }
        return parking.lotsForReserve
    }

    private fun Lot.calculateTimeAvailable() {
        val actual: Long = 1300 //emulating the current time
        this.reservations.sortedBy { it.enDateDateTime } // really I need  order it
        val beforeReservations = this.reservations.filter {
            it.startDateTime < actual // filtering the reservations have begun before current time
        }
        var res = beforeReservations.last() // get the most recent reservation before current

        if(res.enDateDateTime < actual) { // if the most recent reservation has finished then set its
            this.dateTimeAvailable = res.enDateDateTime
        } else {
            var index = reservations.indexOf(res)
            while (index < reservations.size - 1 && dateTimeAvailable == -1L) {
                var space = reservations[index + 1].startDateTime - reservations[index].enDateDateTime
                if(space > 0) {
                    dateTimeAvailable = reservations[index].enDateDateTime
                    if(index < reservations.size - 1) {
                        availableForTime = reservations[index + 1].startDateTime - dateTimeAvailable
                    }
                } else {
                    index++
                }
            }
            if(dateTimeAvailable == -1L) {
                dateTimeAvailable = reservations[index].enDateDateTime
            }
        }
    }
}





