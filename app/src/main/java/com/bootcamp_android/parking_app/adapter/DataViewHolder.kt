package com.bootcamp_android.parking_app.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding
import com.bootcamp_android.parking_app.databinding.FragmentReservationBinding
import com.google.android.material.snackbar.Snackbar

class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private fun bindLot(lot: Lot) {
        val binding = FragmentLotBinding.bind(view)
        binding.textIdLot.text = lot.id
        binding.textDateLot.text = if(!lot.available) lot.date else "free"
        binding.textHourLot.text = lot.hour
        view.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lotListFragment_to_reservationListFragment)
        }
    }

    private fun bindReservation(reservation: Reservation) {
        val binding = FragmentReservationBinding.bind(view)
        binding.textDateStart.text = reservation.startDate
        binding.textHourStart.text = reservation.startHour
        binding.textEndHour.text = reservation.endHour
        binding.textDateEnd.text = reservation.endDate

    }

    fun binding(dataModel: DataModel) {
        when(dataModel) {
            is Lot -> bindLot(dataModel)
            is Reservation -> bindReservation(dataModel)
        }
    }
}