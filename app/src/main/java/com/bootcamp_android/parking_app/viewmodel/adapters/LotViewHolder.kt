package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.util.Utils.formatDateForLotList

import com.bootcamp_android.domain.util.Utils.timeFormat
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding

import com.bootcamp_android.parking_app.utils.Utils


class LotViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(lot: Lot,listener: (Lot) -> Unit) {
        val binding = FragmentLotBinding.bind(view)
        var hour = ""
        var date = Utils.free
        binding.apply {


            if(lot.dateTimeAvailable> System.currentTimeMillis()) {
                date = formatDateForLotList(lot.dateTimeAvailable)
                hour = timeFormat(lot.dateTimeAvailable)

            }

            textHourLot.text =  hour
            textDateLot.text = date
            textIdLot.text = lot.parkingLot.toString()
            itemView.setOnClickListener {
//
                listener(lot)
            }

        }
    }
}

