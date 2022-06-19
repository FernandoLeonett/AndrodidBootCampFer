package com.bootcamp_android.parking_app.viewmodel.adapters

import android.graphics.Color
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

        binding.apply {
            if(lot.free) {
                itemView.setBackgroundColor(Color.parseColor("#567845"));
                textHourLot.text = ""
                textDateLot.text = Utils.free

            } else {
                textHourLot.text = formatDateForLotList(lot.dateTimeAvailable)
                textDateLot.text  = timeFormat(lot.dateTimeAvailable)
            }


            textIdLot.text = lot.parkingLot.toString()
            itemView.setOnClickListener { //
                listener(lot)
            }
        }
    }
}

