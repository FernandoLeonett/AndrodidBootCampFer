package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result

class AddReservationUseCase {

    lateinit var addReservationRepository: IReservationRepository
    suspend operator fun invoke(res: Reservation): Result<Boolean> = addReservationRepository.addReservation(res)
}