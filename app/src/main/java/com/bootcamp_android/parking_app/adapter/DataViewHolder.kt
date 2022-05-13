package com.bootcamp_android.parking_app.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R

class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindLot(lot: Lot) {
        val id = view.findViewById<TextView>(R.id.text_id_lot)
        val date = view.findViewById<TextView>(R.id.text_date_lot)
        val hour = view.findViewById<TextView>(R.id.text_hour_lot)

        id.text = lot.id
        if (lot.available) {
            date.text = lot.date

            hour.text = lot.hour
        } else {
            date.text = "Free"

            hour.text = ""
        }
    }


    fun bindReservation(reservation: Reservation) {
        val startDate = view.findViewById<TextView>(R.id.text_date_start)
        val starHour = view.findViewById<TextView>(R.id.text_hour_start)
        val endDate = view.findViewById<TextView>(R.id.text_date_start)
        val endHour = view.findViewById<TextView>(R.id.text_end_hour)

        startDate.text = reservation.start_date
        starHour.text = reservation.star_hour
        endDate.text = reservation.end_date
        endHour.text = reservation.end_hour
    }

    fun binding(dataModel: DataModel) {
        when (dataModel) {
            is Lot -> bindLot(dataModel)
            is Reservation -> bindReservation(dataModel)
        }
    }
}