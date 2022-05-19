package com.bootcamp_android.parking_app.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding
import java.util.*

class ReservationAddFragment : Fragment(R.layout.fragment_add_reservation) {

    private lateinit var binding: FragmentAddReservationBinding
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        binding = FragmentAddReservationBinding.bind(view)

        binding.apply {
            textSelectStartDateReservation.setOnClickListener {
                showDateTimePickerDialog(R.style.mypickerDialogStart)
            }
            textSelectEndDateReservation.setOnClickListener {
                showDateTimePickerDialog(R.style.mypickerDialogEnd)
            }
        }
        val spinnerList = listOf(
            "Parking Slot 1",
            "Parking Slot 2",
            "Parking Slot 3",
            "Parking Slot 4",
            "Parking Slot 5 final"
        )
        //TODO("this is a test ready to receive arguments")
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinnerList
        )
        binding.lotsOptionsSpinner.apply {
            this.adapter = adapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Toast.makeText(
                            activity,
                            "you have selected ${parent?.getItemAtPosition(position).toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
        }
    }

    private fun showDateTimePickerDialog(style: Int = R.style.mypickerDialogStart) {
        val cal = Calendar.getInstance()
        val dateTimeListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            Toast.makeText(
                activity,
                "$day/$month/$year",
                Toast.LENGTH_LONG
            ).show()
        }

        DatePickerDialog(
            requireContext(),
            style,
            dateTimeListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
        val listenerHora = TimePickerDialog.OnTimeSetListener { _,hour,minutes ->
            Toast.makeText(
                activity,
                "$hour:$minutes",
                Toast.LENGTH_LONG
            ).show()
        }

        TimePickerDialog(
            activity,
            style,
            listenerHora,cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),false
        ).show()
    }
}

