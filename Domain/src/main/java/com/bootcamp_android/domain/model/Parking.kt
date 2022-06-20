package com.bootcamp_android.domain.model

class Parking(
    var lotsForReserve: List<Lot> = emptyList(),var reservations: MutableList<Reservation> = mutableListOf()
)