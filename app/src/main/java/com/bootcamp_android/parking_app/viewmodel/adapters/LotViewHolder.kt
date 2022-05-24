package com.bootcamp_android.parking_app.viewmodel.adapters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding

import com.bootcamp_android.parking_app.utils.Utils
import com.codelab.domain.model.Lot

class LotViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(lot: Lot) {
        val binding = FragmentLotBinding.bind(view)
        binding.apply {
            textIdLot.text = lot.id
            textDateLot.text = if(!lot.available) lot.date else Utils.free
            textHourLot.text = lot.hour
            view.setOnClickListener { view ->

                Toast.makeText(view.context,"Hola",Toast.LENGTH_SHORT).show()
                val bundle: Bundle = bundleOf("KEY_ID" to lot.id)
                view.findNavController().navigate(R.id.btn_lot_to_res,bundle)
            }
        }
    }
}

