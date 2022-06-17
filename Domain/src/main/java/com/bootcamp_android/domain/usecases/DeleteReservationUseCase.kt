package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils.parkingId

class DeleteReservationUseCase {

    lateinit var deleteRepository: IReservationRepository

    suspend operator fun invoke(reservation: Reservation, authorizationCode: String ): Result<Boolean> {
        return deleteRepository.deleteReservation( reservation, authorizationCode)
    }

}