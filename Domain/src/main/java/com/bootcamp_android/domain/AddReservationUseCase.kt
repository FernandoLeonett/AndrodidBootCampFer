package com.bootcamp_android.domain

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result

class AddReservationUseCase {


    lateinit var addReservationRepository: IReservationRepository
    suspend operator fun invoke(parkingId: String, reservation: Reservation): Result<Boolean> {
        return addReservationRepository.addReservation(parkingId,reservation)
    }

}