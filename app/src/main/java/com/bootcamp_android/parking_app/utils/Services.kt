package com.bootcamp_android.parking_app.utils

import java.text.SimpleDateFormat

object Services {

    private fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }

    fun isAvailableLot(startDate: Long,endDate: Long) =
        System.currentTimeMillis() !in startDate..endDate

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