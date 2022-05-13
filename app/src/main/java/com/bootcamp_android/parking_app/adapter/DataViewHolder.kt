package com.bootcamp_android.parking_app.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R

class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindLot(lot: DataModel.Lot) {
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

    fun bindReservation(reservation: DataModel.Reservation) {
        val start_date = view.findViewById<TextView>(R.id.text_date_start)
        val star_hour = view.findViewById<TextView>(R.id.text_hour_start)
        val end_date = view.findViewById<TextView>(R.id.text_date_end)
        val end_hour = view.findViewById<TextView>(R.id.text_end_hour)


        start_date.text = reservation.start_date
        star_hour.text = reservation.star_hour
        end_date.text = reservation.end_date
        end_hour.text = reservation.star_hour
    }

    fun binding(dataModel: DataModel) {
        when (dataModel) {
            is DataModel.Lot -> bindLot(dataModel)
            is DataModel.Reservation -> bindReservation(dataModel)
        }
    }
}