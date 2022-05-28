package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.repostories.ILotsRepository

class LotRepository() : ILotsRepository {

    private val lots = mutableListOf<Lot>()
    private val list = Provider.reservations(30)
    override fun getAllCompleteLots(): MutableList<Lot> {
        for(lot in Provider.lots(10)) {
            for(reservation in list) {
                if(lot.id == reservation.parkingLot) {
                    lot.reservations.add(reservation)
                }
            }
        }
        return lots
    }

    override fun getOneLotWithReservations(id: Int): Lot? {
     return getAllCompleteLots().find { lot-> lot.id==id }
    }
}