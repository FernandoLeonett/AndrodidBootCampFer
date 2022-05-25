package com.bootcamp_android.parking_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentReservationsBinding
import com.bootcamp_android.parking_app.viewmodel.ReservationsViewModel
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import com.bootcamp_android.parking_app.viewmodel.adapters.ReservationAdapter
import com.bootcamp_android.data.repositories.Provider


class ReservationListFragment : Fragment() {

    private lateinit var reservationsViewModel: ReservationsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentReservationsBinding

    override fun onCreateView(  inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?  ): View? {
        viewModelFactory = ViewModelFactory()
        reservationsViewModel = ViewModelProvider(this,viewModelFactory).get(ReservationsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_reservations,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding = FragmentReservationsBinding.bind(itemView)
        binding.recyclerReservationList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ReservationAdapter(Provider.reservations)
        }



        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.fab_res_to_add)
        }
    }
}