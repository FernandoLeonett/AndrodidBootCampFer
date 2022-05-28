package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Lot

interface ILotsRepository {

    fun getAllCompleteLots(): List<Lot>// i want build the lots
    fun getOneLotWithReservations(id: Int): Lot? // this is for second screen
}