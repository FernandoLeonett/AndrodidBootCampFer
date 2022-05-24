package com.codelab.domain

import com.codelab.domain.repostories.ILotsRepository

class GetLotsUseCase {

    lateinit var lotsRepository: ILotsRepository
    operator fun invoke() = lotsRepository.getLots()
}