package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding
import com.bootcamp_android.parking_app.date_picker.DatePickerFragment

class ReservationAddFragment : Fragment(R.layout.fragment_add_reservation) {

    private lateinit var binding: FragmentAddReservationBinding
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        binding = FragmentAddReservationBinding.bind(view)

        binding.apply {
            textSelectStartDateReservation.setOnClickListener {
                showDatePickerDialog(R.style.mypickerDialogStart)
            }
            textSelectEndDateReservation.setOnClickListener {
                showDatePickerDialog(R.style.mypickerDialogEnd)
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

    private fun showDatePickerDialog(style: Int = R.style.mypickerDialogStart) {
        //TODO check for another simple way
        // how can i set different colors for the calendars ready
        val datePickerFragment = DatePickerFragment(style)
        val supportFragmentManager = requireActivity().supportFragmentManager
        // we have to implement setFragmentResultListener
        supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY",
            viewLifecycleOwner
        ) { resultKey,bundle ->
            if(resultKey == "REQUEST_KEY") {
                val date = bundle.getString("SELECTED_DATE")
                binding.textSelectStartDateReservation.text = date
            }
        }

        datePickerFragment.show(supportFragmentManager,"DatePickerFragment")
    }
}
