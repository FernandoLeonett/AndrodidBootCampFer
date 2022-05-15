package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider.Companion.reservations
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding
import com.bootcamp_android.parking_app.databinding.FragmentReservationsBinding

class ReservationListFragment : Fragment(R.layout.fragment_reservations) {



    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        val binding = FragmentReservationsBinding.bind(itemView)
        binding.recyclerReservationList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = Adapter(reservations)
        }
    }
}