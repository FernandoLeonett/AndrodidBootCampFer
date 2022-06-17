package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Lot

interface ILotsRepository {

    suspend fun getDetailLot(id: Int): Lot?
    suspend fun getLots(): List<Lot>

}