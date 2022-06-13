package com.bootcamp_android.domain.model

import com.bootcamp_android.domain.util.Utils

class Parking(private val id: String =Utils.parkingId , var lotsForReserve: List<Lot> = emptyList(), val reservations: MutableList<Reservation> = mutableListOf()) {

     var countSize: Int = lotsForReserve.size
}