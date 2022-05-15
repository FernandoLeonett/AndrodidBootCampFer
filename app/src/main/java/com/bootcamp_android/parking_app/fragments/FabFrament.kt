package com.bootcamp_android.parking_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider
import com.bootcamp_android.parking_app.databinding.FragmentFabBinding
import com.bootcamp_android.parking_app.databinding.FragmentLotsBinding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FabFrament.newInstance] factory method to
 * create an instance of this fragment.
 */
class FabFrament : Fragment() {

    private lateinit var binding: FragmentFabBinding
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFabBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

    }
}