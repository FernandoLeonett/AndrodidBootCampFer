package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.ILotsRepository

class ValidateReservationUseCase {

    lateinit var validateReservationRepository: ILotsRepository

    suspend operator fun  invoke (reservation: Reservation) = validateReservation(reservation )

     private suspend fun validateReservation(reservation: Reservation): Boolean {
        var isValid = true
         validateReservationRepository.getDetailLot(reservation.parkingLot)?.reservations?.forEach {
            if(reservation.endDate in it.starDate..it.endDate || reservation.starDate in it.starDate..it.endDate) {
                isValid = false;
            }
        }
        return isValid
    }
}
