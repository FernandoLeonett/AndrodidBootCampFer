package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.Result
import javax.management.timer.Timer.ONE_MINUTE

class AddReservationUseCase {

    lateinit var addReservationRepository: IReservationRepository

    suspend operator fun invoke(reservation: Reservation) = validateReservation(reservation)
    private val currentTime = System.currentTimeMillis()
    private suspend fun validateReservation(reservation: Reservation): AddResult {
        var isValid = AddResult.IS_FREE

        if(reservation.startDate < currentTime || reservation.parkingLot < 0 || reservation.authorizationCode == "") {
            isValid = AddResult.BAD_REQUEST
        } else if(reservation.endDate < reservation.startDate + ONE_MINUTE) {
            isValid = AddResult.ORDER_DATE
        } else {
            val reservationList = addReservationRepository.getReservations().filter {
                it.parkingLot == reservation.parkingLot
            }
            if(reservationList.isNotEmpty()) {
                reservationList.onEach {
                    if(reservation.endDate in it.startDate..it.endDate || reservation.startDate in it.startDate..it.endDate) {
                        isValid = AddResult.IS_BUSY
                    }
                }
            }
            if(isValid == AddResult.IS_FREE) {
                if(addReservationRepository.addReservation(reservation) !is Result.Success) {
                    isValid = AddResult.Error
                }else{
                    isValid =AddResult.SUCCESS
                }
            }
        }

        return isValid
    }
}