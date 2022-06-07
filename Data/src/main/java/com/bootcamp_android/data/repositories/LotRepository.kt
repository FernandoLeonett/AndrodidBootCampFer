package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.ILotsRepository

class LotRepository : ILotsRepository {

    private  lateinit var reservationsRepository: ReservationRepository

    override fun getDetailLot(id: Int) = getLots().find { it.id == id }

    override fun getLots(): List<Lot> = fillAllLots()

    private fun fillAllLots(): List<Lot> {
        var lotsWithReservations = reservationsRepository.getReservations().groupBy {
            it.parkingLot
        }.map {
            Lot(it.key,it.value as MutableList<Reservation>)
        }
        val allLots = Provider.lots(13)
        allLots.forEach { emptyLot ->
            val any = lotsWithReservations.find {
                it.id == emptyLot.id
            }
            if(any != null) {
                emptyLot.reservations = any.reservations
            }
        }
        return allLots
    }
}