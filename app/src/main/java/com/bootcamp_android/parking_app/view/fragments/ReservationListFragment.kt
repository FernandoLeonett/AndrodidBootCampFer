package com.bootcamp_android.parking_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentReservationsBinding
import com.bootcamp_android.parking_app.view_model.Provider.Companion.reservations
import com.bootcamp_android.parking_app.view_model.adapter.Adapter

class ReservationListFragment : Fragment() {

    private lateinit var lotId: String
    private lateinit var binding: FragmentReservationsBinding
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_reservations,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding = FragmentReservationsBinding.bind(itemView)
        binding.recyclerReservationList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = Adapter(reservations)
        }

        arguments?.let {
            lotId = it.getString("KEY_ID").toString()
            binding.textTitleReservations.text = "lot # $lotId";
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.from_reservations_to_add)
        }
    }
}