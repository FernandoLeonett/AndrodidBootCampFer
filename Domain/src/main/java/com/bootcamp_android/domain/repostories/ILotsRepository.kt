package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.util.Result

interface ILotsRepository {

    suspend fun getLots(): List<Lot>
}