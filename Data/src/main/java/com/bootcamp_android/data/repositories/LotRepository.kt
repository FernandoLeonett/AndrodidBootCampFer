package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.repositories.Provider.Companion.lots
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.repostories.ILotsRepository

class LotRepository : ILotsRepository {

    override fun getLots(): List<Lot> = lots
}