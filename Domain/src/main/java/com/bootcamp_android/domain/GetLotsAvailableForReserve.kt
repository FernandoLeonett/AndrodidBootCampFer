package com.bootcamp_android.domain

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Utils

class GetLotsAvailableForReserve {
//TODO calculate the availability
    lateinit var reservationRepository: IReservationRepository
    operator fun invoke():List<Reservation> =
        reservationRepository.getAllReservations().filter { r -> Utils.isCurrentReservation(r.enDateDateTime) }
}


