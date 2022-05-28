package com.bootcamp_android.domain

import com.bootcamp_android.domain.repostories.ILotsRepository

class GetLotsUseCase {

    lateinit var lotsRepository: ILotsRepository
    operator fun invoke() = lotsRepository.getAllCompleteLots() //TODO("i don know if i need it")
}

