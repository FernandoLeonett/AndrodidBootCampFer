package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.domain.util.Utils.reservationCardFormatDate
import com.bootcamp_android.domain.util.Utils.timeFormatLot
import com.bootcamp_android.parking_app.databinding.FragmentReservationBinding

class ReservationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(reservation: LotDetail,listener: (Int) -> Unit) {
        val binding = FragmentReservationBinding.bind(view)
        binding.apply {
            textDateStart.text =
                reservationCardFormatDate(reservation.startDateTime)
            textDateEnd.text =
                reservationCardFormatDate(reservation.enDateDateTime)
            textEndHour.text =
                timeFormatLot(reservation.enDateDateTime)
            textHourStart.text =
                timeFormatLot(reservation.startDateTime)
           btnDeleteReservation.setOnClickListener { listener(reservation.parkingLot) }
        }
    }
}


