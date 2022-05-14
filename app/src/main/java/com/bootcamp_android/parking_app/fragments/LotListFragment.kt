package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider.Companion.lots
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding

class LotListFragment : Fragment() {

    private lateinit var binding: FragmentLotsBinding
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLotsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding.recyclerLotList
        binding.recyclerLotList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = Adapter(lots)
        }
    }
}