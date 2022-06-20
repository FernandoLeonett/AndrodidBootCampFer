package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Lot

interface ILotsRepository {

    suspend fun getLots(): List<Lot>
}