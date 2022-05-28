package com.bootcamp_android.domain

import com.bootcamp_android.domain.repostories.IReservationRepository

class GetReservationsUseCase {

    lateinit var reservationRepository: IReservationRepository
    operator fun invoke() = reservationRepository.getAllReservations()
}