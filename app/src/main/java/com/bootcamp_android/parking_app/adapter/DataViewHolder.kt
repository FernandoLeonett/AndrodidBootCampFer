package com.bootcamp_android.parking_app.adapter

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding
import com.bootcamp_android.parking_app.databinding.FragmentReservationBinding

class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private fun bindLot(lot: Lot) {
        val binding = FragmentLotBinding.bind(view)
        binding.apply {
            textIdLot.text = lot.id
            textDateLot.text = if(!lot.available) lot.date else "free"
            textHourLot.text = lot.hour
            view.setOnClickListener { view ->
                view.findNavController().navigate(R.id.action_lotListFragment_to_reservationListFragment)
            }
        }
    }

    private fun bindReservation(reservation: Reservation) {
        val binding = FragmentReservationBinding.bind(view)
        binding.apply {
            textDateStart.text = reservation.startDate
            textHourStart.text = reservation.startHour
            textEndHour.text = reservation.endHour
            textDateEnd.text = reservation.endDate
            btnDeleteReservation.setOnClickListener { view ->
                val builder = AlertDialog.Builder(view.context)

                builder.setTitle("Alert!").setMessage("Are you sure do you want delete this reservation ?")
                    .setCancelable(true) // dialog box in cancellable
                    // set positive button
                    //take two parameters dialogInterface and an int
                    .setPositiveButton("Yes") { dialogInterface,_ ->
                        Toast.makeText(view.context,"me borraron",Toast.LENGTH_SHORT).show()
                        dialogInterface.dismiss()
                    }.setNegativeButton("No") { dialogInterface,_ -> // cancel the dialogbox
                        dialogInterface.cancel()
                    }.setNeutralButton("Help") { dialogInterface,_ -> // just show a toast
                        Toast.makeText(view.context,"Help Clicked",Toast.LENGTH_SHORT).show()
                    } // show the builder
                    .show()
            }
        }
    }

    fun binding(dataModel: DataModel) {
        when(dataModel) {
            is Lot -> bindLot(dataModel)
            is Reservation -> bindReservation(dataModel)
        }
    }
}