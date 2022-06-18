package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.repostories.ILotsRepository

class GetDetailALotUseCase {

    lateinit var lotRepository: ILotsRepository

    suspend fun getLotDetail(id: Int): Lot? {
        return lotRepository.getLotByID(id)
    }
}