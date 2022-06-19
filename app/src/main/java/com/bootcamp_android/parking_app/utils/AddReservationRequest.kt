package com.bootcamp_android.parking_app.utils

import com.bootcamp_android.domain.model.Reservation

data class AddReservationRequest(
    val reservation: Reservation,
    var lot: Boolean = true,
    var startDate: Boolean = true,
    var endDate: Boolean = true,
    var orderDate: Boolean = true,
    var authorizationCode: Boolean = true,
    var successRequest: Boolean = false
) {

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
        if(reservation.endDate < reservation.startDate) {
            orderDate = false
        }
        successRequest = orderDate && endDate && startDate && lot && authorizationCode
    }
}
