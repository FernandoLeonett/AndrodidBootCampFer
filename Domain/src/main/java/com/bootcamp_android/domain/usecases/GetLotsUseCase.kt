package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.repostories.ILotsRepository

class GetLotsUseCase {

    lateinit var lotRepository: ILotsRepository

    suspend fun getLots(): List<Lot> {
        var lots = lotRepository.getLots(true)
        if(lots.isEmpty()) {
            lots = lotRepository.getLots(false)
        }
        return lots
    }
}





