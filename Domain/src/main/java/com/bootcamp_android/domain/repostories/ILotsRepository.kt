package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.Lot

interface ILotsRepository {

//    suspend fun getLotByID(id: Int): Lot?
    suspend fun getLots(): List<Lot>
//     suspend fun getLotByIDFromDataBase(id: Int): Lot

}