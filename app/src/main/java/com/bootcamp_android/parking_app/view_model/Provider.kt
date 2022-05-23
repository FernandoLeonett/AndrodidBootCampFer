package com.bootcamp_android.parking_app.view_model

import com.bootcamp_android.parking_app.model.Lot
import com.bootcamp_android.parking_app.model.Reservation

class Provider { companion object {

    val lots = lotList(100)
    val reservations = reservationList(100)
    private fun lotList(n: Int): MutableList<Lot> {
        val lots = mutableListOf<Lot>()

        for(i in 1..n) {
            lots.add(Lot("$i","Monday, 25 May,2022","8:30 pm",true))
        }
        return lots
    }

    private fun reservationList(n: Int): MutableList<Reservation> {
        val reservations = mutableListOf<Reservation>()

        for(i in 1..n) {
            reservations.add(
                Reservation(
                    "Monday 25 May 2022","Monday 25 May 2022","7:30 am","8:30 am"
                )
            )
        }
        return reservations
    }
}
}
