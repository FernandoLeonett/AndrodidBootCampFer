package com.bootcamp_android.parking_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import com.bootcamp_android.parking_app.viewmodel.adapters.LotsAdapter
import com.bootcamp_android.parking_app.viewmodel.lots.LotsViewModel

class LotsFragment : Fragment() {

    //LotDetailViewModel
    private lateinit var lotsViewModel: LotsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var binding: FragmentLotsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        viewModelFactory = ViewModelFactory()
        lotsViewModel = ViewModelProvider(
            this,viewModelFactory
        ).get(LotsViewModel::class.java)

        lotsViewModel.lots.observe(viewLifecycleOwner) {
            binding?.recyclerLotList?.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = LotsAdapter(it) { v -> lotClick(v) }
            }
        }
        return inflater.inflate(R.layout.fragment_lots,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding = FragmentLotsBinding.bind(itemView)

        binding?.apply {
            fab.setOnClickListener {
                findNavController().navigate(R.id.fab_lot_to_add)
            }
        }
    }

    private fun lotClick(lot: Lot) {
        val action = LotsFragmentDirections.btnLotToRes(lot.id)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

