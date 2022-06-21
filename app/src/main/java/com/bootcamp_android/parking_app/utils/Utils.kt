package com.bootcamp_android.parking_app.utils

import java.text.SimpleDateFormat

object Utils {

    const val FREE: String = "Free"
    const val spinnerDefaultValue = "Select a Lot"
    const val FREE_COLOR = "#606c38"
    const val BUSY_COLOR = "#283618"
    const val PLACEHOLDER_LOT = "Lot "

    private fun String.capitalizeWords() = split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }

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

    fun timeAvailable(time: Long): String {
        val hour = time / 3600000
        val minutes = (time % 3600000 * 0.000017).toInt()
        val msg = if(time != -1L) {
            "free for: $hour hours $minutes min"
        } else {
            "Totally Free"
        }
        return msg
    }
    fun formatDateInput(time: Long):String{
       val format =  SimpleDateFormat("dd/MM/yyyy HH:mm");
      return  format.format(time)

    }
}