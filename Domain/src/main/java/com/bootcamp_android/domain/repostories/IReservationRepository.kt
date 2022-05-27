package com.bootcamp_android.domain.repostories

import com.bootcamp_android.domain.model.LotDetail

open interface IReservationRepository {

    fun getReservations(): List<LotDetail>
}