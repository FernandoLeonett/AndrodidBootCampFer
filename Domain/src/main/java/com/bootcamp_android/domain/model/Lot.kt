package com.bootcamp_android.domain.model

import java.io.Serializable

data class Lot(val parkingLot: Int,var reservations: MutableList<Reservation> = mutableListOf()): Serializable {

    var dateTimeAvailable: Long = -1
    var availableForTime: Long =-1
}