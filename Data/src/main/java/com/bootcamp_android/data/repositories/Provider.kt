package com.bootcamp_android.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation

class Provider {

    companion object {

        private const val starDateTest = 1650308957285 //apr 2022
        private const val endDateTest = 1654718874072 // jun 2022

        fun reservations(n: Int): MutableList<Reservation> {
            val reservations = mutableListOf<Reservation>()

            for(i in 1..n) {
                reservations.add(Reservation("1234",starDateTest,(starDateTest..endDateTest).random(),i))
            }
            Log.d(TAG,"reservations: api: $reservations")
            return reservations
        }

        fun lots(n: Int): MutableList<Lot> {
            val lots = mutableListOf<Lot>()
            for(i in 1..n) {
                lots.add(Lot(i))
            }
            return lots
        }
    }
}


