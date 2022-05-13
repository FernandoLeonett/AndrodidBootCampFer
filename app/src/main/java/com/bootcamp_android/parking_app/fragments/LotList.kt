package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding

class LotList : Fragment() {
    private lateinit var _binding: FragmentLotsBinding
    private lateinit var dataAdapter: Adapter
    private val binding: FragmentLotsBinding = _binding
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLotsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding.lotList
            .apply {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
                hasFixedSize()
                this.adapter = dataAdapter
                dataAdapter.setData(Provider.lots)
            }
    }
}