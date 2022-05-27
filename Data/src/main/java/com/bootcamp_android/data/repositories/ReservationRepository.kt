package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.domain.repostories.IReservationRepository

class ReservationRepository : IReservationRepository {

    override fun getReservations(): List<LotDetail> = Provider.reservations
}