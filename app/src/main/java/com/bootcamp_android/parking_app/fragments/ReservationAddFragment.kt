package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding

class ReservationAddFragment : Fragment(R.layout.fragment_add_reservation) {

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        val binding = FragmentAddReservationBinding.bind(view)
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
}
