package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Lot
import com.bootcamp_android.parking_app.adapter.Provider
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding

class LotList : Fragment() {
    private var _binding: FragmentLotsBinding? = null
    private val binding: FragmentLotsBinding get() = _binding!!
    private lateinit var dataAdapter: Adapter


    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLotsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {


        Toast.makeText(activity,"PASÉ POR AQUÍ",Toast.LENGTH_SHORT).show(); //Correcto
//        binding.recyclerLotList
//            .apply {
//                layoutManager = LinearLayoutManager(activity)
//
//                adapter = dataAdapter
//                dataAdapter.setData(Provider.lots)
//            }
    }
}