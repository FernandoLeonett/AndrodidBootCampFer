package com.bootcamp_android.parking_app.viewmodel.adapters

import android.app.AlertDialog
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.parking_app.databinding.FragmentReservationBinding
import com.bootcamp_android.parking_app.utils.Services

class ReservationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(reservation: LotDetail,listener: (Int) -> Unit) {
        val binding = FragmentReservationBinding.bind(view)
        binding.apply {
            textDateStart.text =
                Services.reservationCardFormatDate(reservation.startDateTime)
            textDateEnd.text =
                Services.reservationCardFormatDate(reservation.enDateDateTime)
            textEndHour.text =
                Services.timeFormatLot(reservation.enDateDateTime)
            textHourStart.text =
                Services.timeFormatLot(reservation.startDateTime)
           btnDeleteReservation.setOnClickListener { listener(reservation.parkingLot) }
        }
    }
}


