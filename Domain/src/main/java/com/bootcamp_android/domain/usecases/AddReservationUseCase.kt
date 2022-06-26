package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.Result
import javax.management.timer.Timer.ONE_MINUTE

class AddReservationUseCase {

    private lateinit var isValid: AddResult
    lateinit var addReservationRepository: IReservationRepository

    suspend operator fun invoke(reservation: Reservation) = validateReservation(reservation)
    private val currentTime = System.currentTimeMillis()
    private suspend fun validateReservation(reservation: Reservation): AddResult {
        if(reservation.startDate < currentTime || reservation.parkingLot < 0 || reservation.authorizationCode == "") {
            isValid = AddResult.AddResponse.REQUEST
        } else if(reservation.endDate < reservation.startDate + ONE_MINUTE) {
            isValid = AddResult.AddResponse.ORDER
        } else {
            val reservationList = addReservationRepository.getReservations().filter {
                it.parkingLot == reservation.parkingLot
            }
            if(reservationList.isNotEmpty()) {
                reservationList.onEach {
                    if(reservation.endDate in it.startDate..it.endDate || reservation.startDate in it.startDate..it.endDate) {
                        isValid = AddResult.AddResponse.BUSY
                    }
                }
            }
            if(!this::isValid.isInitialized) {
                isValid = if(addReservationRepository.addReservation(reservation) !is Result.Success) {
                    AddResult.AddResponse.Error
                } else {
                    AddResult.AddResponse.SUCCESS
                }
            }
        }

        return isValid
    }
}