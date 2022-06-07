package com.bootcamp_android.domain.model

data class Lot(val id: Int,var reservations: MutableList<Reservation> = mutableListOf()) {

    var dateTimeAvailable: Long = -1
    var availableForTime: Long =-1
}