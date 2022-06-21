package com.bootcamp_android.parking_app.utils

import com.bootcamp_android.domain.model.Reservation

data class AddReservationRequest(
    var lot: Boolean = true,
    var startDate: Boolean = true,
    var endDate: Boolean = true,
    var orderDate: Boolean = true,
    var authorizationCode: Boolean = true,
    var successRequest: Boolean = false,
) {

    operator fun invoke(reservation: Reservation) = validate(reservation)

    private fun validate(reservation: Reservation): AddReservationRequest {
        val ok = AddReservationRequest()
        if(reservation.authorizationCode == "") {
            ok.authorizationCode = false
        }
        if(reservation.parkingLot == -1) {
            ok.lot = false
        }
        if(reservation.endDate == -1L) {
            ok.endDate = false
        }
        if(reservation.startDate == -1L) {
            ok.startDate = false
        }
        if(reservation.endDate < reservation.startDate) {
            ok.orderDate = false
        }
        ok.successRequest = orderDate && endDate && startDate && lot && authorizationCode

        return ok
    }
}
