package com.bootcamp_android.parking_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding
import com.bootcamp_android.parking_app.view_model.Provider.Companion.lots
import com.bootcamp_android.parking_app.view_model.adapter.Adapter

class LotListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_lots,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {



        val binding = FragmentLotsBinding.bind(itemView)


        binding.apply {
            recyclerLotList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = Adapter(lots)
            }
            fab.setOnClickListener {
                findNavController().navigate(R.id.from_lots_to_add)
            }
        }
    }
}