package com.bootcamp_android.parking_app.utils

import java.text.SimpleDateFormat

object Utils {

    const val FREE: String = "Free"
    const val BUSY: String = "busy"
    const val spinnerDefaultValue = "Select a Lot"
    const val FREE_COLOR = "#606c38"
    const val BUSY_COLOR = "#283618"
    const val PLACEHOLDER_LOT = "Lot "

    private fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }

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