package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.Utils.formatRatforReservationList
import com.bootcamp_android.domain.util.Utils.timeFormat
import com.bootcamp_android.parking_app.databinding.FragmentReservationBinding

class ReservationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(reservation: Reservation,listener: (Reservation,Int) -> Unit, pos:Int) {
        val binding = FragmentReservationBinding.bind(view)
        binding.apply {
            textDateStart.text =
                formatRatforReservationList(reservation.starDate)
            textDateEnd.text =
                formatRatforReservationList(reservation.endDate)
            textEndHour.text =
                timeFormat(reservation.starDate)
            textHourStart.text =
                timeFormat(reservation.starDate)
           btnDeleteReservation.setOnClickListener { listener(reservation,pos) }
        }
    }
}


