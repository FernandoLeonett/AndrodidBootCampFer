package com.bootcamp_android.domain.util

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.IResultAdd

class AddReservationRequest(
    val reservation: Reservation,
    var lot: Boolean = true,
    var startDate: Boolean = true,
    var endDate: Boolean = true,
    var authorizationCode: Boolean = true,
    var successRequest: Boolean = false,
) : IResultAdd {

    init {
        validate()
    }

    private fun validate() {
        if(reservation.authorizationCode == "") {
            authorizationCode = false
        }
        if(reservation.parkingLot == -1) {
            lot = false
        }
        if(reservation.endDate == -1L) {
            endDate = false
        }
        if(reservation.startDate == -1L) {
            startDate = false
        }

        successRequest = endDate && startDate && lot && authorizationCode
    }
}
