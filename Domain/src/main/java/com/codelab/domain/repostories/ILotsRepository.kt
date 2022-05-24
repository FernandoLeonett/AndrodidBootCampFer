package com.codelab.domain.repostories

import com.codelab.domain.model.Lot

interface ILotsRepository {

    fun getLots(): List<Lot>
}