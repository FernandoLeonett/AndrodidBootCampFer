package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentFabBinding

class FabFrament : Fragment(R.layout.fragment_fab) {

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        val binding = FragmentFabBinding.bind(itemView)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_lotListFragment_to_fragmentAddReservation)
            binding.fab.hide()
        }
    }
}