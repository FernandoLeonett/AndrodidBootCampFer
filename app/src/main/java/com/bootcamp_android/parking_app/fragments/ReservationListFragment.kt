package com.bootcamp_android.parking_app.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider.Companion.reservations
import com.bootcamp_android.parking_app.databinding.FragmentReservationsBinding

class ReservationListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        val binding = FragmentReservationsBinding.bind(itemView)
        binding.recyclerReservationList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = Adapter(reservations)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.from_reservations_to_add)
        }


    }
}