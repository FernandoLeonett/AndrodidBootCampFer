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
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding
import com.bootcamp_android.parking_app.viewmodel.LotsViewModel
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import com.bootcamp_android.parking_app.viewmodel.adapters.LotAdapter

class LotListFragment : Fragment() {

    private lateinit var lotsViewModel: LotsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentLotsBinding
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        viewModelFactory = ViewModelFactory()
        lotsViewModel = ViewModelProvider(this,viewModelFactory).get(LotsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_lots,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding = FragmentLotsBinding.bind(itemView)
        var lots = lotsViewModel.requireLots()
        binding.apply {
            recyclerLotList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = LotAdapter(lots)
            }
            fab.setOnClickListener {
                findNavController().navigate(R.id.fab_lot_to_add)
            }
        }
    }
}