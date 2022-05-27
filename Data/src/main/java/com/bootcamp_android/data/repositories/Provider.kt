package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.LotDetail

class Provider {

    companion object {

        val reservations = reservations(60)
        val lots = lots(10)
        private fun reservations(n: Int): MutableList<LotDetail> {
            val reservations = mutableListOf<LotDetail>()

            for(i in 1..n) {
                reservations.add(LotDetail("1234",233254543636,433254543636,i))
            }
            return reservations
        }

        private fun lots(n: Int): MutableList<Lot> {
            val lots = mutableListOf<Lot>()
            for(i in 1..n) {
                lots.add(Lot(i))
            }
            return lots
        }
    }
}


