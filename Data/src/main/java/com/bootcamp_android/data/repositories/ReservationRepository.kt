package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Utils

class ReservationRepository : IReservationRepository {

    override fun getAllReservations(): List<LotDetail> = Provider.reservations(30)
    override fun getReservationsCurrentAvailability(): List<LotDetail> =
        getAllReservations().filter { r-> Utils.isCurrentReservation(r.enDateDateTime) }

    override fun addReservation(res: LotDetail): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteReservation(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    operator fun invoke() = getAllReservations()
}