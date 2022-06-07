package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.repostories.ILotsRepository

class LotRepository : ILotsRepository {

    override suspend fun getDetailLot(id: Int) = getLots().find { it.id == id }

    override suspend fun getLots(): List<Lot> = Provider.lots(13)
}