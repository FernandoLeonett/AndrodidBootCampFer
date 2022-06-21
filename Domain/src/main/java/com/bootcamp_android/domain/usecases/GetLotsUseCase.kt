package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.repostories.ILotsRepository

class GetLotsUseCase {

    lateinit var lotRepository: ILotsRepository

    suspend fun getLots() = lotRepository.getLots()
}





