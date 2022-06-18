package com.bootcamp_android.parking_app.utils

import java.text.SimpleDateFormat

object Utils {

    const val free:String= "Free"
    const val oneHourMilliseconds = 3600000
    const val  oneMinuteMilliseconds =60000


    private fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } } //    fun isMarkedAsFree(startDate: Long,endDate: Long) = System.currentTimeMillis() !in startDate..endDate
    fun formatDateForLotList(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEEE d MMMM yyyy")
        return simpleDateFormat.format(date).capitalizeWords()
    }

    fun timeFormat(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("h:mm a")
        return simpleDateFormat.format(date).replace(".","").uppercase()
    }

    fun formatRatforReservationList(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEEE d \n MMMM yyyy")
        return simpleDateFormat.format(date).capitalizeWords()
    }


}