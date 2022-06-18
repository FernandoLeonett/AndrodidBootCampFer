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
import com.bootcamp_android.parking_app.viewmodel.LotsViewModel

class LotsFragment : Fragment() {

    //LotDetailViewModel
    private lateinit var lotsViewModel: LotsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
//    private var spinnerList = mutableListOf<String>()

    private var binding: FragmentLotsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ViewModelFactory(requireContext())
        lotsViewModel = ViewModelProvider(
            this,viewModelFactory
        ).get(LotsViewModel::class.java)


        return inflater.inflate(R.layout.fragment_lots,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding = FragmentLotsBinding.bind(itemView)
        lotsViewModel.loadLots()

        lotsViewModel.lots.observe(viewLifecycleOwner) { lots ->
//            spinnerList.clear()
            initRecycleLots(lots)
//            lots.map {
//                spinnerList.add("Lot: ${it.parkingLot}")
//            }

        }

        binding?.apply {

            fab.setOnClickListener {

                val action = LotsFragmentDirections.fabLotToAdd()
                findNavController().navigate(action)
            }
        }
    }

    private fun lotClick(lot: Lot) {
        val action = LotsFragmentDirections.btnLotToRes(lot,lot.parkingLot.toString())
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initRecycleLots(lots: List<Lot>) {
        binding?.recyclerLotList?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = LotsAdapter(lots) { lot -> lotClick(lot) }
        }
    }
}

