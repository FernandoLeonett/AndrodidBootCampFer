package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository

class ValidateReservationUseCase {

    lateinit var validateReservationRepository: IReservationRepository

    suspend operator fun invoke(reservation: Reservation) = validateReservation(reservation)

    private suspend fun validateReservation(reservation: Reservation): Boolean {
        var isValid = true
        validateReservationRepository.getReservations().filter {
            it.parkingLot == reservation.parkingLot
        }.also { reservations ->
            reservations.forEach {


                if(reservation.endDate in it.startDate..it.endDate || reservation.startDate in it.startDate..it.endDate) {
                    isValid = false;
                }
            }
            return isValid
        }
    }
}