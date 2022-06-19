package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.AddResult

class ValidateReservationUseCase {

    lateinit var validateReservationRepository: IReservationRepository

    suspend operator fun invoke(reservation: Reservation) = validateReservation(reservation)

    private suspend fun validateReservation(reservation: Reservation): AddResult {
        var isValid = AddResult.IS_FREE
        validateReservationRepository.getReservations().filter {
            it.parkingLot == reservation.parkingLot
        }.also { reservations ->
            reservations.forEach {


                if(reservation.endDate in it.startDate..it.endDate || reservation.startDate in it.startDate..it.endDate) {
                    isValid = AddResult.IS_BUSY;
                }
            }
            return isValid
        }
    }
}