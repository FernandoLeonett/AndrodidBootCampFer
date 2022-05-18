package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding

class FragmentAddReservation : Fragment(R.layout.fragment_add_reservation) {

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val binding = FragmentAddReservationBinding.bind(view)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.slots_options_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.lotsOptionsSpinner.adapter = adapter
        }
    }
}
