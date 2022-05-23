package com.bootcamp_android.parking_app.view_model.adapter

import android.app.AlertDialog
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding
import com.bootcamp_android.parking_app.databinding.FragmentReservationBinding
import com.bootcamp_android.parking_app.model.DataModel
import com.bootcamp_android.parking_app.model.Lot
import com.bootcamp_android.parking_app.model.Reservation

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

                builder.setTitle("Delete Reservation")
                val input =
                    EditText(view.context) // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                input.clipToOutline
                builder.setView(input)
                    .setMessage("Are you sure you want to delete this reservation? Please input the authorization code to confirm")
                    .setCancelable(true) // dialog box in cancellable
                    // set positive button
                    //take two parameters dialogInterface and an int
                    .setPositiveButton("OK") { dialogInterface,_ ->
                        Toast.makeText(view.context,"me borraron",Toast.LENGTH_SHORT).show()
                        dialogInterface.dismiss()
                    }.setNegativeButton("CANCEL") { dialogInterface,_ -> // cancel the dialogbox
                        dialogInterface.cancel()
                    }.show()
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