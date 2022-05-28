package com.bootcamp_android.domain

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.ILotsRepository
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Utils

class GetDetailALotUseCase {
    lateinit var lotRepository: ILotsRepository

}