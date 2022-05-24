package com.codelab.data.repositories

import com.codelab.data.repositories.Provider.Companion.lots
import com.codelab.domain.model.Lot
import com.codelab.domain.repostories.ILotsRepository

class LotRepository : ILotsRepository {

    override fun getLots(): List<Lot> = lots
}