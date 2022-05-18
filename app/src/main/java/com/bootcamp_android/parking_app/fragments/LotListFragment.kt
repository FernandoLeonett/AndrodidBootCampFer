package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider.Companion.lots
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding

class LotListFragment : Fragment(R.layout.fragment_lots) {

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