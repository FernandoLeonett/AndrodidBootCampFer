package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.repositories.Provider.Companion.reservations
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.domain.repostories.ILotsRepository
import com.bootcamp_android.domain.repostories.IReservationRepository

class LotRepository : ILotsRepository {

    override fun getLots(): List<Lot> = Provider.lots
}