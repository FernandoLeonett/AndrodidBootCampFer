package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.repostories.IReservationRepository


class GetReservationsUseCase {

    lateinit var reservationRepository: IReservationRepository

    suspend fun getReservation() = reservationRepository.getReservations()
}