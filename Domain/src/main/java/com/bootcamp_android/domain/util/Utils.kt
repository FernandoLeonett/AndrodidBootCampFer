package com.bootcamp_android.domain.util

import java.text.SimpleDateFormat

object Utils {
    const val parkingId:String= "N0TUMxRLEatjw7G8jsw"
    fun isCurrentReservation(endDate: Long) = endDate > System.currentTimeMillis()
    private fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }

    fun isMarkedAsFree(startDate: Long,endDate: Long) = System.currentTimeMillis() !in startDate..endDate

    fun fullDateFormatLot(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEEE d MMMM yyyy")
        return simpleDateFormat.format(date).capitalizeWords()
    }

    fun timeFormatLot(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("h:mm a")
        return simpleDateFormat.format(date).replace(".","").uppercase()
    }

    fun reservationCardFormatDate(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEEE d \n MMMM yyyy")
        return simpleDateFormat.format(date).capitalizeWords()
    }
}