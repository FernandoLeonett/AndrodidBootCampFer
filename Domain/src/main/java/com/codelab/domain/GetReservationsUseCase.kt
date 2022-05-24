package com.codelab.domain

import com.codelab.domain.repostories.IReservationRepository

class GetReservationsUseCase {

    lateinit var reservationRepository: IReservationRepository
    operator fun invoke() = reservationRepository.getReservations()
}