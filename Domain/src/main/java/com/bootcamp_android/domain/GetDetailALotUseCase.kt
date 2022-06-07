package com.bootcamp_android.domain

import com.bootcamp_android.domain.repostories.ILotsRepository

class GetDetailALotUseCase {

    lateinit var lotRepository: ILotsRepository

    operator fun invoke(id: Int) = lotRepository.getDetailLot(id)
}