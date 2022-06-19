package com.bootcamp_android.domain.model

import java.io.Serializable

data class Lot(val parkingLot: Int,var reservations: MutableList<Reservation> = mutableListOf()): Serializable {

    var free: Boolean = false
    var dateTimeAvailable: Long = -1
    var availableForTime: Long =-1

    fun calculateTimeAvailable() {
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
            } else {
                var index = reservations.indexOf(res)
                while (index < reservations.size - 1 && dateTimeAvailable == -1L) {
                    var space = reservations[index + 1].startDate - reservations[index].endDate
                    if(space > 0) {
                        dateTimeAvailable = reservations[index].endDate
                        if(index < reservations.size - 1) {
                            availableForTime = space
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